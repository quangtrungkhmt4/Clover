package com.ben.clover.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.ben.clover.LoginRegisterActivity;
import com.ben.clover.MainActivity;
import com.ben.clover.R;
import com.ben.clover.constant.Gender;
import com.ben.clover.constant.ResponseCode;
import com.ben.clover.network.DataClient;
import com.ben.clover.network.RetrofitInstance;
import com.ben.clover.request.RegisterRequest;
import com.ben.clover.response.RegisterResponse;
import com.ben.clover.response.ResponseEntity;
import com.ben.clover.util.DialogUtil;
import com.ben.clover.util.PreferencesUtil;
import com.ben.clover.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment implements View.OnClickListener {

    private EditText edtUserName, edtPass, edtRePass, edtDay, edtMonth, edtYear, edtMail, edtName;
    private RadioButton radMale;
    private Button btnRegister;
    private LinearLayout lnDateOfBirth;
    private LoginRegisterActivity loginRegisterActivity;
    private Dialog dialogProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        findId(view);
        initViews();
        return view;
    }

    private void findId(View view) {
        edtUserName = view.findViewById(R.id.edtUserNameRegister);
        edtPass = view.findViewById(R.id.edtPasswordRegister);
        edtRePass = view.findViewById(R.id.edtRePasswordRegister);
        edtDay = view.findViewById(R.id.edtDateBirthRegister);
        edtMonth = view.findViewById(R.id.edtMonthBirthRegister);
        edtYear = view.findViewById(R.id.edtYearBirthRegister);
        edtMail = view.findViewById(R.id.edtEmailRegister);
        radMale = view.findViewById(R.id.radMale);
        btnRegister = view.findViewById(R.id.btnRegister);
        lnDateOfBirth = view.findViewById(R.id.lnDateOfBirth);
        edtName = view.findViewById(R.id.edtNameRegister);
    }

    private void initViews() {
        loginRegisterActivity = (LoginRegisterActivity) getActivity();
        dialogProgress = DialogUtil.initDialogProcess(getContext());
        lnDateOfBirth.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lnDateOfBirth:
                final Calendar currentDate = Calendar.getInstance();
                final Calendar date = Calendar.getInstance();
                new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.set(year, monthOfYear, dayOfMonth);
                        String month = monthOfYear < 10 ? "0" + (monthOfYear+1) : String.valueOf(monthOfYear+1);
                        String day = dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth);
                        edtDay.setText(day);
                        edtMonth.setText(month);
                        edtYear.setText(String.valueOf(year));
                    }
                }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
                break;
            case R.id.btnRegister:
                String userName = edtUserName.getText().toString();
                String pass = edtPass.getText().toString();
                String rePass = edtRePass.getText().toString();
                String day = edtDay.getText().toString();
                String month = edtMonth.getText().toString();
                String year = edtYear.getText().toString();
                String mail = edtMail.getText().toString();
                String name = edtName.getText().toString();
                int gender = radMale.isChecked() ? Gender.MALE : Gender.FEMALE;

                if (!StringUtil.validString(userName, pass, rePass, day, month, year, mail, name)){
                    DialogUtil.snackBarNoti(btnRegister, getString(R.string.pleaseInputFullInfo));
                    return;
                }

                if (!pass.equals(rePass)){
                    DialogUtil.snackBarNoti(btnRegister, getString(R.string.twoPassNotEqual));
                    return;
                }

                if (!StringUtil.validateMail(mail)){
                    DialogUtil.snackBarNoti(btnRegister, getString(R.string.mailInvalid));
                    return;
                }

                RegisterRequest registerRequest = RegisterRequest.builder()
                        .userName(userName)
                        .dayOfBirth(day)
                        .monthOfBirth(month)
                        .yearOfBirth(year)
                        .mail(mail)
                        .name(name)
                        .password(pass)
                        .gender(gender).build();
                dialogProgress.show();
                callRequest(registerRequest);
                break;
        }
    }

    private void callRequest(RegisterRequest registerRequest){
        DataClient dataClient = RetrofitInstance.getInstance().create(DataClient.class);
        Call<JsonObject> call = dataClient.register(registerRequest);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                dialogProgress.dismiss();
                Type type = new TypeToken<ResponseEntity<RegisterResponse>>(){}.getType();
                ResponseEntity<RegisterResponse> entity = new Gson().fromJson(response.body().toString(), type);
                if (entity.getCode() != 0){
                    DialogUtil.initDialogNoti(getContext(), ResponseCode.getMessage(getContext(), entity.getCode()));
                    return;
                }

                String loginJson = new Gson().toJson(entity.getData());
                PreferencesUtil.saveData(PreferencesUtil.LOGIN_RESPONSE_KEY, loginJson, loginRegisterActivity);
                loginRegisterActivity.switchActivity(MainActivity.class);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                dialogProgress.dismiss();
                DialogUtil.initDialogNoti(getContext(), t.getMessage());
            }
        });
    }
}