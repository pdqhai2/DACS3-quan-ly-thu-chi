package com.example.moma;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moma.RequestPack.User.login.LoginRequest;
import com.example.moma.ResponsePack.User.login.LoginResponse;
import com.example.moma.RetrofitPack.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private int DELAY_TIME = 200;

    SaveData saveData = new SaveData();

    EditText user_phone, user_pass, user_pass2;
    LoginRequest loginRequest;
    LoginResponse loginResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registor);

        user_phone = findViewById(R.id.new_phone);
        user_pass = findViewById(R.id.new_pass);
        user_pass2 = findViewById(R.id.new_pass2);

        findViewById(R.id.new_btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!user_pass.getText().toString().equals(user_pass2.getText().toString()))
                {
                    Toast.makeText(Register.this, "Mat khau khac nhau!", Toast.LENGTH_SHORT).show();
                }
                else {
                    LoginRequest request = new LoginRequest();
                    request.setUser_pass(user_pass.getText().toString());
                    request.setUser_phone(user_phone.getText().toString());
                    Call<LoginResponse> call = RetrofitClient.getRetrofitClient().register(request);
                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                loginResponse = response.body();
                                if (!loginResponse.isSuccess()) {
                                    Toast.makeText(Register.this, "Error!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Register.this, "Thanh cong!", Toast.LENGTH_SHORT).show();
                                    saveData.pushUser(getApplicationContext(), loginResponse.getUser().getUser_id());
                                    //intent
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(getApplicationContext(),Home.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }, DELAY_TIME);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Log.d("ERROR", "onFailure: " + t.getLocalizedMessage());
                        }
                    });
                }
            }
        });
    }
}
