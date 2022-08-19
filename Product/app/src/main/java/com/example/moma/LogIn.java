package com.example.moma;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moma.RequestPack.User.login.LoginRequest;
import com.example.moma.ResponsePack.User.login.LoginResponse;
import com.example.moma.RetrofitPack.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {

    private int DELAY_TIME = 200;

    SaveData saveData = new SaveData();

    LoginRequest loginRequest = new LoginRequest();
    LoginResponse loginResponse;
    EditText user_phone, user_pass;
    CheckBox checkBox;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        user_phone = findViewById(R.id.login_phone);
        user_pass = findViewById(R.id.login_pass);
        checkBox = findViewById(R.id.checkbox);

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        user_phone.setText(sharedPreferences.getString("userphone",""));
        user_pass.setText(sharedPreferences.getString("userpass",""));
        checkBox.setChecked(sharedPreferences.getBoolean("checked",false));

        findViewById(R.id.login_btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginRequest.setUser_pass(user_pass.getText().toString());
                loginRequest.setUser_phone(user_phone.getText().toString());
                Call<LoginResponse> call = RetrofitClient.getRetrofitClient().getUser(loginRequest);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            loginResponse = response.body();
                            if (!loginResponse.isSuccess()) {
                                showDialogER();
                            } else {
                                //luu thong tin dang nhap
                                saveData.pushCheckBox(getApplicationContext(),checkBox.isChecked());
                                saveData.pushUser(getApplicationContext(), loginResponse.getUser().getUser_id());

                                showDialogOK();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.d("ERROR", "onFailure: " + t.getLocalizedMessage());
                    }
                });
            }
        });

        findViewById(R.id.login_btnNew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regisIntent = new Intent(LogIn.this, Register.class);
                startActivity(regisIntent);
                finish();
            }
        });
    }

    private void showDialogOK()
    {
        final Button next;
        final Dialog dialog = new Dialog(LogIn.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_ok);

        Window window = dialog.getWindow();
        if (window == null) {}

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windownAtrribute = window.getAttributes();
        windownAtrribute.gravity = Gravity.CENTER;
        window.setAttributes(windownAtrribute);

        next = dialog.findViewById(R.id.dialog_nextBtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(dialog.getContext(),Home.class);
                        startActivity(intent);
                        finish();
                    }
                }, DELAY_TIME);
            }
        });

        dialog.show();
    }

    private void showDialogER()
    {
        final Button next;
        final TextView error;
        final Dialog dialog = new Dialog(LogIn.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_fail);

        Window window = dialog.getWindow();
        if (window == null) {}

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windownAtrribute = window.getAttributes();
        windownAtrribute.gravity = Gravity.CENTER;
        window.setAttributes(windownAtrribute);

        error = dialog.findViewById(R.id.dialog_showError);
        error.setText("SAI THÔNG TIN ĐĂNG NHẬP");

        next = dialog.findViewById(R.id.dialog_retryBtn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}