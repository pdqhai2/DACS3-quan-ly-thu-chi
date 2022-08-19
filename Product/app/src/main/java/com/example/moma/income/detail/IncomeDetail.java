package com.example.moma.income.detail;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moma.Home;
import com.example.moma.R;
import com.example.moma.RequestPack.Income.del.IncomeDelRequest;
import com.example.moma.RequestPack.Income.detail.IncomeDetailRequest;
import com.example.moma.ResponsePack.income.detail.IncomeDetailResponse;
import com.example.moma.ResponsePack.income.list.IncomeListResponse;
import com.example.moma.RetrofitPack.RetrofitClient;
import com.example.moma.SaveData;
import com.example.moma.income.add.IncomeAddForm;
import com.example.moma.income.edit.IncomeEdit;
import com.example.moma.income.edit.IncomeEditTypeList;
import com.example.moma.income.list.IncomeList;
import com.example.moma.outcome.list.OutcomeList;
import com.example.moma.thongke.thongkeMain;
import com.example.moma.user.UserDetail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomeDetail extends AppCompatActivity {

    int DELAY_TIME = 500;

    SaveData saveData = new SaveData();

    private int income_id, user_id;
    IncomeDetailRequest incomeDetailRequest = new IncomeDetailRequest();
    IncomeDetailResponse incomeDetailResponse;

    TextView detail_income_id, detail_income_name, detail_income_val, detail_income_type, detail_income_date;
    String name, day, val, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_detail);

        menu();

        Intent detail = getIntent();
        income_id = detail.getIntExtra("income_id",0);
        user_id = saveData.getUser(IncomeDetail.this);

        incomeDetailRequest.setIncome_id(income_id);

        detail_income_id = findViewById(R.id.detail_income_id);
        detail_income_name = findViewById(R.id.detail_income_name);
        detail_income_val = findViewById(R.id.detail_income_val);
        detail_income_type = findViewById(R.id.detail_income_type);
        detail_income_date = findViewById(R.id.detail_income_date);

        detail_income_id.setText(String.valueOf(income_id));

        saveData.pushIncome(getApplicationContext(), income_id);


        findViewById(R.id.detail_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), IncomeEditTypeList.class);
                startActivity(intent);
                finish();
//                showDialogER("HỆ THỐNG BẢO TRÌ");
            }
        });

        findViewById(R.id.detail_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogWarning();
            }
        });

        Call<IncomeDetailResponse> call = RetrofitClient.getRetrofitClient().getIncomeDetail(incomeDetailRequest);
        call.enqueue(new Callback<IncomeDetailResponse>() {
            @Override
            public void onResponse(Call<IncomeDetailResponse> call, Response<IncomeDetailResponse> response) {
                if (response.isSuccessful())
                {
                    incomeDetailResponse = response.body();


                    name = incomeDetailResponse.getIncome().getIncome_name();
                    val = incomeDetailResponse.getIncome().getIncome_valstring();
                    day = incomeDetailResponse.getIncome().getIncome_day() + " / " + incomeDetailResponse.getIncome().getIncome_month() + " / " + incomeDetailResponse.getIncome().getIncome_year();
                    type = incomeDetailResponse.getIntype().getIntype_name();

                    detail_income_name.setText(name);
                    detail_income_val.setText(val);
                    detail_income_type.setText(type);
                    detail_income_date.setText(day);
                }
            }

            @Override
            public void onFailure(Call<IncomeDetailResponse> call, Throwable t) {
                Log.d("ERROR", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void menu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_income);

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

    private void showDialogWarning()
    {
        final Button next, cancel;
        final Dialog dialog = new Dialog(IncomeDetail.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_warning);

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
                IncomeDelRequest incomeDelRequest = new IncomeDelRequest();
                incomeDelRequest.setIncome_id(income_id);
                incomeDelRequest.setUser_id(user_id);
                Call<IncomeListResponse> call2 = RetrofitClient.getRetrofitClient().delIncome(incomeDelRequest);
                call2.enqueue(new Callback<IncomeListResponse>() {
                    @Override
                    public void onResponse(Call<IncomeListResponse> call, Response<IncomeListResponse> response) {
                        if (response.isSuccessful())
                        {
                            IncomeListResponse incomeListResponse = response.body();
                            if (incomeListResponse.isSuccess())
                            {
                                showDialogOK();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<IncomeListResponse> call, Throwable t) {

                    }
                });
                dialog.dismiss();
            }
        });

        cancel = dialog.findViewById(R.id.dialog_cancelBtn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showDialogOK()
    {
        final Button next;
        final Dialog dialog = new Dialog(IncomeDetail.this);
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
                        dialog.dismiss();
                        Intent intent = new Intent(dialog.getContext(), IncomeList.class);
                        startActivity(intent);
                        finish();
                    }
                }, DELAY_TIME);
            }
        });

        dialog.show();
    }

    private void showDialogER(String errorString)
    {
        final Button next;
        final TextView error;
        final Dialog dialog = new Dialog(IncomeDetail.this);
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
        error.setText(errorString);

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
