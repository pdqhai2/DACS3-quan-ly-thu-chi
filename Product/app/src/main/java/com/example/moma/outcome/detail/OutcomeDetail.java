package com.example.moma.outcome.detail;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.moma.RequestPack.outcome.del.OutcomeDelRequest;
import com.example.moma.RequestPack.outcome.detail.OutcomeDetailRequest;
import com.example.moma.ResponsePack.income.list.IncomeListResponse;
import com.example.moma.ResponsePack.outcome.detail.OutcomeDetailResponse;
import com.example.moma.ResponsePack.outcome.list.OutcomeListResponse;
import com.example.moma.RetrofitPack.RetrofitClient;
import com.example.moma.SaveData;
import com.example.moma.income.detail.IncomeDetail;
import com.example.moma.income.list.IncomeList;
import com.example.moma.outcome.list.OutcomeList;
import com.example.moma.thongke.thongkeMain;
import com.example.moma.user.UserDetail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutcomeDetail extends AppCompatActivity {
    int DELAY_TIME = 500;

    SaveData saveData = new SaveData();

    OutcomeDetailRequest outcomeDetailRequest = new OutcomeDetailRequest();
    OutcomeDetailResponse outcomeDetailResponse = new OutcomeDetailResponse();

    private int outcome_id, user_id;

    TextView detail_outcome_id, detail_outcome_name, detail_outcome_val, detail_outcome_type, detail_outcome_date;
    String name, day, val, type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outcome_detail);

        menu();

        Intent detail = getIntent();
        outcome_id = detail.getIntExtra("outcome_id",0);
        user_id = saveData.getUser(OutcomeDetail.this);

        outcomeDetailRequest.setOutcome_id(outcome_id);

        detail_outcome_id = findViewById(R.id.detail_income_id);
        detail_outcome_name = findViewById(R.id.detail_income_name);
        detail_outcome_val = findViewById(R.id.detail_income_val);
        detail_outcome_type = findViewById(R.id.detail_income_type);
        detail_outcome_date = findViewById(R.id.detail_income_date);

        detail_outcome_id.setText(String.valueOf(outcome_id));

        saveData.pushOutcome(getApplicationContext(), outcome_id);

        findViewById(R.id.detail_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogWarning();
            }
        });

        Call<OutcomeDetailResponse> call = RetrofitClient.getRetrofitClient().getOutcomeDetail(outcomeDetailRequest);
        call.enqueue(new Callback<OutcomeDetailResponse>() {
            @Override
            public void onResponse(Call<OutcomeDetailResponse> call, Response<OutcomeDetailResponse> response) {
                if (response.isSuccessful())
                {
                    outcomeDetailResponse = response.body();

                    name = outcomeDetailResponse.getOutcome().getOutcome_name();
                    val = outcomeDetailResponse.getOutcome().getOutcome_valstring();
                    day = outcomeDetailResponse.getOutcome().getOutcome_day() + " / " + outcomeDetailResponse.getOutcome().getOutcome_month() + " / " + outcomeDetailResponse.getOutcome().getOutcome_year();
                    type = outcomeDetailResponse.getOuttype().getOuttype_name();

                    detail_outcome_name.setText(name);
                    detail_outcome_val.setText(val);
                    detail_outcome_type.setText(type);
                    detail_outcome_date.setText(day);
                }
            }

            @Override
            public void onFailure(Call<OutcomeDetailResponse> call, Throwable t) {

            }
        });
    }
    private void menu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_outcome);

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
        final Dialog dialog = new Dialog(OutcomeDetail.this);
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
                OutcomeDelRequest incomeDelRequest = new OutcomeDelRequest();
                incomeDelRequest.setOutcome_id(outcome_id);
                incomeDelRequest.setUser_id(user_id);
                Call<OutcomeListResponse> call2 = RetrofitClient.getRetrofitClient().delOutcome(incomeDelRequest);
                call2.enqueue(new Callback<OutcomeListResponse>() {
                    @Override
                    public void onResponse(Call<OutcomeListResponse> call, Response<OutcomeListResponse> response) {
                        if (response.isSuccessful())
                        {
                            OutcomeListResponse incomeListResponse = response.body();
                            if (incomeListResponse.isSuccess())
                            {
                                showDialogOK();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<OutcomeListResponse> call, Throwable t) {

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
        final Dialog dialog = new Dialog(OutcomeDetail.this);
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
                        Intent intent = new Intent(dialog.getContext(), OutcomeList.class);
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
        final Dialog dialog = new Dialog(OutcomeDetail.this);
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
