package com.ben.clover.fragment;

import android.app.Dialog;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ben.clover.LoginRegisterActivity;
import com.ben.clover.MainActivity;
import com.ben.clover.R;
import com.ben.clover.constant.ResponseCode;
import com.ben.clover.network.DataClient;
import com.ben.clover.network.RetrofitInstance;
import com.ben.clover.request.ForgotPasswordRequest;
import com.ben.clover.request.LoginRequest;
import com.ben.clover.response.RegisterResponse;
import com.ben.clover.response.ResponseEntity;
import com.ben.clover.response.SuccessResponse;
import com.ben.clover.util.DialogUtil;
import com.ben.clover.util.PreferencesUtil;
import com.ben.clover.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText edtUserName, edtPassword;
    private Button btnLogin;
    private TextView tvForgotPass, tvRegister;
    private LoginRegisterActivity loginRegisterActivity;
    private Dialog dialogProgress;
    private  DataClient dataClient;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        findId(view);
        initViews();
        return view;
    }

    private void findId(View view) {
        edtUserName = view.findViewById(R.id.edtUserNameLogin);
        edtPassword = view.findViewById(R.id.edtPasswordLogin);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvForgotPass = view.findViewById(R.id.tvForgotPassword);
        tvRegister = view.findViewById(R.id.tvRegister);
    }

    private void initViews() {
        loginRegisterActivity = (LoginRegisterActivity) getActivity();
        dataClient = RetrofitInstance.getInstance().create(DataClient.class);
        tvRegister.setPaintFlags(tvRegister.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgotPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();

                if (!StringUtil.validString(userName, password)){
                    DialogUtil.snackBarNoti(btnLogin, getString(R.string.pleaseInputFullInfo));
                    return;
                }
                LoginRequest request = new LoginRequest(userName, password);
                dialogProgress = DialogUtil.initDialogProcess(getContext());
                dialogProgress.show();
                login(request);
                break;
            case R.id.tvForgotPassword:
                String userNameForgot = edtUserName.getText().toString();

                if (!StringUtil.validString(userNameForgot) || !userNameForgot.contains(",")){
                    DialogUtil.initDialogNoti(getContext(), getString(R.string.confirmForgotPass));
                    return;
                }

                String[] infos = userNameForgot.split(",");
                String user = infos[0].trim();
                String mail = infos[1].trim();

                ForgotPasswordRequest requestForgot = new ForgotPasswordRequest(user, mail);
                dialogProgress = DialogUtil.initDialogProcess(getContext());
                dialogProgress.show();
                forgotPassword(requestForgot);

                break;
            case R.id.tvRegister:
                loginRegisterActivity.switchToRegister();
                break;
        }
    }

    private void forgotPassword(ForgotPasswordRequest request){
        Call<JsonObject> call = dataClient.forgotPassword(request);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                dialogProgress.dismiss();
                Type type = new TypeToken<ResponseEntity<SuccessResponse>>(){}.getType();
                ResponseEntity<SuccessResponse> entity = new Gson().fromJson(response.body().toString(), type);
                if (entity.getCode() != 0){
                    DialogUtil.initDialogNoti(getContext(), ResponseCode.getMessage(getContext(), entity.getCode()));
                    return;
                }
                DialogUtil.snackBarNoti(btnLogin, getString(R.string.notiForgotPass));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                dialogProgress.dismiss();
                DialogUtil.initDialogNoti(getContext(), t.getMessage());
            }
        });
    }

    private void login(LoginRequest request){
        Call<JsonObject> call = dataClient.login(request);
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