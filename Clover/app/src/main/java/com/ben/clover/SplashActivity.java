package com.ben.clover;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import com.ben.clover.constant.ResponseCode;
import com.ben.clover.network.DataClient;
import com.ben.clover.network.RetrofitInstance;
import com.ben.clover.request.CheckActiveRequest;
import com.ben.clover.response.RegisterResponse;
import com.ben.clover.response.ResponseEntity;
import com.ben.clover.response.SuccessResponse;
import com.ben.clover.util.CheckNetworkConnection;
import com.ben.clover.util.DialogUtil;
import com.ben.clover.util.PermissionUtils;
import com.ben.clover.util.PreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = this.getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
//        }

        if (!PermissionUtils.checkPermission(this) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            startActivity(new Intent(this, PermissionActivity.class));
            finish();
            return;
        }

        new CheckNetworkConnection(this, new CheckNetworkConnection.OnConnectionCallback() {

            @Override
            public void onConnectionSuccess() {
                CountDownTimer timer = new CountDownTimer(2000,1000) {
                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        String token = getToken();
                        if (token == null){
                            switchLogin();
                        }else {
                            checkActive(token);
                        }
                    }
                };
                timer.start();
            }

            @Override
            public void onConnectionFail(String msg) {
                DialogUtil.initDialogNoti(SplashActivity.this, getString(R.string.internet_error));
            }
        }).execute();


    }

    private void checkActive(String token){
        CheckActiveRequest request = new CheckActiveRequest(token);
        DataClient dataClient = RetrofitInstance.getInstance().create(DataClient.class);
        Call<JsonObject> call = dataClient.checkActive(request);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Type type = new TypeToken<ResponseEntity<SuccessResponse>>(){}.getType();
                ResponseEntity<SuccessResponse> entity = new Gson().fromJson(response.body().toString(), type);
                if (entity.getCode() != 0){
                    switchLogin();
                    return;
                }
                switchMain();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                DialogUtil.initDialogNoti(SplashActivity.this, t.getMessage());
            }
        });
    }

    private String getToken(){
        String loginDataStr = PreferencesUtil.getData(PreferencesUtil.LOGIN_RESPONSE_KEY, this);
        RegisterResponse loginData = new Gson().fromJson(loginDataStr, RegisterResponse.class);
        if (loginData == null){
            return null;
        }
        return loginData.getToken();
    }

    private void switchLogin(){
        Intent intent = new Intent(SplashActivity.this, LoginRegisterActivity.class);
        startActivity(intent);
        finish();
    }

    private void switchMain(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}