package com.example.moma.user;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moma.Home;
import com.example.moma.LogIn;
import com.example.moma.R;
import com.example.moma.RequestPack.User.detail.UserDetailRequest;
import com.example.moma.ResponsePack.User.login.LoginResponse;
import com.example.moma.RetrofitPack.RetrofitClient;
import com.example.moma.SaveData;
import com.example.moma.income.list.IncomeList;
import com.example.moma.outcome.list.OutcomeList;
import com.example.moma.thongke.thongkeMain;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDetail extends AppCompatActivity {

    SaveData saveData = new SaveData();
    int user_id;
    UserDetailRequest userDetailRequest = new UserDetailRequest();

    TextView name, fullname, phone, sodu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_infor);

        fullname = findViewById(R.id.full_name);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);

        user_id = saveData.getUser(getApplicationContext());

        userDetailRequest.setUser_id(user_id);
        Call<LoginResponse> call = RetrofitClient.getRetrofitClient().getUserDetail(userDetailRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful())
                {
                    LoginResponse loginResponse = response.body();
                    if(loginResponse.isSuccess())
                    {
                        fullname.setText(loginResponse.getUser().getUser_name());
                        name.setText(loginResponse.getUser().getUser_name());
                        phone.setText(loginResponse.getUser().getUser_phone());
//                        sodu.setTextColor(Color.rgb(255,0,0));
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

        menu();

        findViewById(R.id.user_logoutBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData.removeUser(getApplicationContext());
                saveData.pushCheckBox(getApplicationContext(), false);
                Intent intent = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void menu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_user);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        Intent menu_CateList = new Intent(getApplicationContext(), Home.class);
                        startActivity(menu_CateList);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_income:
                        Intent menu_PostList = new Intent(getApplicationContext(), IncomeList.class);
                        startActivity(menu_PostList);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_outcome:
                        Intent menu_PostDetail = new Intent(getApplicationContext(), OutcomeList.class);
                        startActivity(menu_PostDetail);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_thongke:
                        Intent thongke = new Intent(getApplicationContext(), thongkeMain.class);
                        startActivity(thongke);
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_user:
                        Intent infor = new Intent(getApplicationContext(), UserDetail.class);
                        startActivity(infor);
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}
