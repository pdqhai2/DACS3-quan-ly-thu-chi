package com.example.moma.income.edit;

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
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moma.Home;
import com.example.moma.R;
import com.example.moma.RequestPack.Income.add.IncomeAddRequest;
import com.example.moma.RequestPack.Income.detail.IncomeDetailRequest;
import com.example.moma.ResponsePack.income.detail.IncomeDetailResponse;
import com.example.moma.ResponsePack.income.list.IncomeListResponse;
import com.example.moma.RetrofitPack.RetrofitClient;
import com.example.moma.SaveData;
import com.example.moma.income.detail.IncomeDetail;
import com.example.moma.income.list.IncomeList;
import com.example.moma.outcome.list.OutcomeList;
import com.example.moma.thongke.thongkeMain;
import com.example.moma.user.UserDetail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IncomeEdit extends AppCompatActivity {
    EditText income_name, income_val, income_day, income_month, income_year;
    Button confirm, today,money;
    IncomeAddRequest incomeAddRequest = new IncomeAddRequest();
    IncomeDetailRequest incomeDetailRequest = new IncomeDetailRequest();
    IncomeDetailResponse incomeDetailResponse = new IncomeDetailResponse();
    IncomeListResponse incomeListResponse;
    boolean editable = true;

    int user_id,val1, val2, intype_id, income_id, DELAY_TIME = 500;
    String name, day, month, year, value = "";

    SaveData saveData = new SaveData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_edit_form);

        menu();

        income_name = findViewById(R.id.income_add_name);
        income_val = findViewById(R.id.income_add_val);
        income_day = findViewById(R.id.income_add_day);
        income_month = findViewById(R.id.income_add_month);
        income_year = findViewById(R.id.income_add_year);
        today = findViewById(R.id.income_add_todayBtn);
        confirm = findViewById(R.id.income_add_confirm);
        money = findViewById(R.id.income_add_editformat);

        Intent intent = getIntent();
        user_id = saveData.getUser(getApplicationContext());

        intype_id = intent.getIntExtra("intype_id",0);
        income_id = saveData.getIncome(getApplicationContext());
        name = intent.getStringExtra("intype_name");

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editable == true)
                {
                    money.setText("NH???P L???I");
                    income_val.setEnabled(false);
                    if (income_val.getText().length() != 0 )
                    {
                        value = income_val.getText().toString();
                    }
                    else value = "0";
                    income_val.setText(formatMoney(value) + " VND");
                    income_val.setTextColor(Color.rgb(100,100,100));
                    editable = false;
                }
                else
                {
                    money.setText("X??C NH???N GI?? TI???N");
                    income_val.setEnabled(true);
                    income_val.setText(value);
                    editable = true;
                }

            }
        });

        incomeDetailRequest.setIncome_id(income_id);
        Call<IncomeDetailResponse> call = RetrofitClient.getRetrofitClient().getIncomeDetailEdit(incomeDetailRequest);
        call.enqueue(new Callback<IncomeDetailResponse>() {
            @Override
            public void onResponse(Call<IncomeDetailResponse> call, Response<IncomeDetailResponse> response) {
                if (response.isSuccessful())
                {
                    incomeDetailResponse = response.body();
                    if(incomeDetailResponse.isSuccess())
                    {
                        income_name.setText(incomeDetailResponse.getIncome().getIncome_name());
                        income_val.setText(String.valueOf(incomeDetailResponse.getIncome().getIncome_val() * 1000 + incomeDetailResponse.getIncome().getIncome_val_2()));
                        income_day.setText(incomeDetailResponse.getIncome().getIncome_day());
                        income_month.setText(incomeDetailResponse.getIncome().getIncome_month());
                        income_year.setText(incomeDetailResponse.getIncome().getIncome_year());
                        saveData.removeIncome(getApplicationContext());
                    }
                }
            }

            @Override
            public void onFailure(Call<IncomeDetailResponse> call, Throwable t) {

            }
        });

        findViewById(R.id.income_add_cancel_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), IncomeDetail.class);
                intent.putExtra("income_id", income_id);
                startActivity(intent1);
                finish();
            }
        });

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                income_day.setText(String.valueOf(calendar.get(Calendar.DATE)));
                income_month.setText(String.valueOf(calendar.get(Calendar.MONTH) + 1));
                income_year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value.length() != 0 && editable == false)
                {
                    name = income_name.getText().toString();
                    val1 = Integer.parseInt(value);
                    val2 = val1 % 1000;
                    val1 = val1 / 1000;
                    day = income_day.getText().toString();
                    month = income_month.getText().toString();
                    year = income_year.getText().toString();
                    incomeAddRequest = new IncomeAddRequest();

                    if(checknull() && checkdate())
                    {
                        incomeAddRequest.setIncome_id(income_id);
                        incomeAddRequest.setIncome_name(name);
                        incomeAddRequest.setIncome_val(val1);
                        incomeAddRequest.setIncome_val_2(val2);
                        incomeAddRequest.setIncome_valstring(income_val.getText().toString());
                        incomeAddRequest.setIncome_day(day);
                        incomeAddRequest.setIncome_month(month);
                        incomeAddRequest.setIncome_year(year);
                        incomeAddRequest.setIntype_id(intype_id);
                        incomeAddRequest.setUser_id(user_id);

                        Call<IncomeDetailResponse> call = RetrofitClient.getRetrofitClient().editIncome(incomeAddRequest);
                        call.enqueue(new Callback<IncomeDetailResponse>() {
                            @Override
                            public void onResponse(Call<IncomeDetailResponse> call, Response<IncomeDetailResponse> response) {
                                if (response.isSuccessful())
                                {
                                    IncomeDetailResponse response1 = response.body();
                                    if (response1.isSuccess())
                                    {
                                        showDialogOK();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<IncomeDetailResponse> call, Throwable t) {

                            }
                        });
                    }
                    else showDialogER("??I???N ?????Y ????? TH??NG TIN");
                }
                else showDialogER("VUI L??NG X??C NH???N GI?? TI???N");
            }
        });
    }

    private String formatMoney(String number)
    {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0");
        return decimalFormat.format(Double.parseDouble(number));
    }

    private boolean checkdate() {
        Calendar calendar = Calendar.getInstance();
        int dayst, monthst, yearst, dayin, monthin, yearin;
        dayst = calendar.get(Calendar.DATE);
        monthst = calendar.get(Calendar.MONTH) + 1;
        yearst = calendar.get(Calendar.YEAR);

        dayin = Integer.parseInt(day);
        monthin = Integer.parseInt(month);
        yearin = Integer.parseInt(year);

//        if (yearin > yearst) return false;
//        else if (monthin > monthst) return false;
//        else if (dayin > dayst) return false;
//        else
        if (monthin == 2 && dayin > 29) return false;
        else if (dayin > 31) return false;
        else if (monthin > 12) return false;
        else return true;
    }

    private boolean checknull() {
        if (!name.isEmpty() && ! income_val.getText().toString().isEmpty() && !day.isEmpty() && !month.isEmpty() && !year.isEmpty()) {
            return true;
        }
        else return false;
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

    private void showDialogOK()
    {
        final Button next;
        final Dialog dialog = new Dialog(IncomeEdit.this);
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
                        Intent intent = new Intent(dialog.getContext(), IncomeDetail.class);
                        intent.putExtra("income_id", income_id);
                        startActivity(intent);
                        finish();
                    }
                }, DELAY_TIME);
            }
        });

        dialog.show();
    }

    private void showDialogER(String st)
    {
        final Button next;
        final TextView error;
        final Dialog dialog = new Dialog(IncomeEdit.this);
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
        error.setText(st);

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
