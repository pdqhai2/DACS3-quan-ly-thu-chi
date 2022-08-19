package com.example.moma.outcome.add;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moma.Home;
import com.example.moma.R;
import com.example.moma.RequestPack.Income.add.IncomeAddRequest;
import com.example.moma.RequestPack.outcome.add.OutcomeAddRequest;
import com.example.moma.ResponsePack.income.list.IncomeListResponse;
import com.example.moma.ResponsePack.outcome.list.OutcomeListResponse;
import com.example.moma.RetrofitPack.RetrofitClient;
import com.example.moma.SaveData;
import com.example.moma.income.add.IncomeAddForm;
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

public class OutcomeAddForm extends AppCompatActivity {

    private int DELAY_TIME = 500;

    EditText outcome_name, outcome_val, outcome_day, outcome_month, outcome_year;
    Button confirm, today, money;
    OutcomeAddRequest outcomeAddRequest = new OutcomeAddRequest();
    OutcomeListResponse outcomeListResponse = new OutcomeListResponse();

    int user_id, outtype_id, val1, val2;
    String name, day, month, year, value = "";
    boolean editable = true;

    SaveData saveData = new SaveData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outcome_add_form);

        menu();

        outcome_name = findViewById(R.id.outcome_add_name);
        outcome_val = findViewById(R.id.outcome_add_val);
        outcome_day = findViewById(R.id.outcome_add_day);
        outcome_month = findViewById(R.id.outcome_add_month);
        outcome_year = findViewById(R.id.outcome_add_year);
        today = findViewById(R.id.outcome_add_todayBtn);
        confirm = findViewById(R.id.outcome_add_confirm);

        Intent intent = getIntent();
        user_id = saveData.getUser(getApplicationContext());
        outtype_id = intent.getIntExtra("outtype_id",0);
        name = intent.getStringExtra("outtype_name");
        money = findViewById(R.id.outcome_add_editformat);

        outcome_name.setText(name + " ");

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editable == true)
                {
                    money.setText("NHẬP LẠI");
                    outcome_val.setEnabled(false);
                    if (outcome_val.getText().length() != 0 )
                    {
                        value = outcome_val.getText().toString();
                    }
                    else value = "0";
                    outcome_val.setText(formatMoney(value) + " VND");
                    outcome_val.setTextColor(Color.rgb(100,100,100));
                    editable = false;
                }
                else
                {
                    money.setText("XÁC NHẬN GIÁ TIỀN");
                    outcome_val.setEnabled(true);
                    outcome_val.setText(value);
                    editable = true;
                }

            }
        });

        findViewById(R.id.outcome_add_cancel_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), OutcomeList.class);
                startActivity(intent1);
                finish();
            }
        });

        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                outcome_day.setText(String.valueOf(calendar.get(Calendar.DATE)));
                outcome_month.setText(String.valueOf(calendar.get(Calendar.MONTH) + 1));
                outcome_year.setText(String.valueOf(calendar.get(Calendar.YEAR)));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value.length() != 0 && editable == false) {
                    name = outcome_name.getText().toString();
                    val1 = Integer.parseInt(value);
                    val2 = val1 % 1000;
                    val1 = val1 / 1000;
                    day = outcome_day.getText().toString();
                    month = outcome_month.getText().toString();
                    year = outcome_year.getText().toString();

                    if ( checknull() && checkdate() ) {
                        outcomeAddRequest.setOutcome_name(name);
                        outcomeAddRequest.setOutcome_val(val1);
                        outcomeAddRequest.setOutcome_val_2(val2);
                        outcomeAddRequest.setOutcome_valstring(outcome_val.getText().toString());
                        outcomeAddRequest.setOutcome_day(day);
                        outcomeAddRequest.setOutcome_month(month);
                        outcomeAddRequest.setOutcome_year(year);
                        outcomeAddRequest.setOuttype_id(outtype_id);
                        outcomeAddRequest.setUser_id(user_id);

                        Call<OutcomeListResponse> call = RetrofitClient.getRetrofitClient().addOutcome(outcomeAddRequest);
                        call.enqueue(new Callback<OutcomeListResponse>() {
                            @Override
                            public void onResponse(Call<OutcomeListResponse> call, Response<OutcomeListResponse> response) {
                                if (response.isSuccessful()) {
                                    outcomeListResponse = response.body();
                                    if (outcomeListResponse.isSuccess()) {
                                        showDialogOK();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<OutcomeListResponse> call, Throwable t) {

                            }
                        });
                    } else showDialogER("ĐIỀN ĐẦY ĐỦ THÔNG TIN");
                } else showDialogER("VUI LÒNG XÁC NHẬN GIÁ TIỀN");
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
        if (!name.isEmpty() && !outcome_val.getText().toString().isEmpty() && !day.isEmpty() && !month.isEmpty() && !year.isEmpty()) {
            return true;
        }
        else return false;
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

    private void showDialogOK()
    {
        final Button next;
        final Dialog dialog = new Dialog(OutcomeAddForm.this);
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
                        intent.putExtra("user_id", user_id);
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
        final Dialog dialog = new Dialog(OutcomeAddForm.this);
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
