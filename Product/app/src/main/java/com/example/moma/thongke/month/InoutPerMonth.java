package com.example.moma.thongke.month;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import com.example.moma.RequestPack.thongke.InoutPerMonthRequest;
import com.example.moma.ResponsePack.thongke.InoutPerMonthResponse;
import com.example.moma.RetrofitPack.RetrofitClient;
import com.example.moma.SaveData;
import com.example.moma.income.list.IncomeList;
import com.example.moma.outcome.list.OutcomeList;
import com.example.moma.thongke.thongkeMain;
import com.example.moma.user.UserDetail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InoutPerMonth extends AppCompatActivity {

    int user_id, sumin, sumout;
    EditText thang, nam;
    SaveData saveData = new SaveData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inout_per_month);

        user_id = saveData.getUser(getApplicationContext());

        thang = findViewById(R.id.month);
        nam = findViewById(R.id.year);

        findViewById(R.id.backBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), thongkeMain.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(thang.getText().length() > 0 && nam.getText().length() > 0)
                {
                    InoutPerMonthRequest inoutPerMonthRequest = new InoutPerMonthRequest();
                    inoutPerMonthRequest.setUser_id(user_id);
                    inoutPerMonthRequest.setMonth(thang.getText().toString());
                    inoutPerMonthRequest.setYear(nam.getText().toString());
                    Call<InoutPerMonthResponse> call = RetrofitClient.getRetrofitClient().inoutPerMonth(inoutPerMonthRequest);
                    call.enqueue(new Callback<InoutPerMonthResponse>() {
                        @Override
                        public void onResponse(Call<InoutPerMonthResponse> call, Response<InoutPerMonthResponse> response) {
                            if (response.isSuccessful())
                            {
                                InoutPerMonthResponse inoutPerMonthResponse = response.body();
                                if (inoutPerMonthResponse.isSuccess())
                                {
                                    if(inoutPerMonthResponse.getSumincome() != null)
                                    {
                                        sumin = inoutPerMonthResponse.getSumincome().getSi_val()*1000 + inoutPerMonthResponse.getSumincome().getSi_val_2();
                                    } else sumin = 0;
                                    if(inoutPerMonthResponse.getSumoutcome() != null)
                                    {
                                        sumout = inoutPerMonthResponse.getSumoutcome().getSo_val()*1000 + inoutPerMonthResponse.getSumoutcome().getSo_val_2();
                                    } else sumout = 0;
                                    Intent intent = new Intent(getApplicationContext(), InoutPerMonthDetail.class);
                                    intent.putExtra("sumin", sumin);
                                    intent.putExtra("sumout", sumout);
                                    intent.putExtra("thang", thang.getText().toString());
                                    intent.putExtra("nam", nam.getText().toString());
                                    startActivity(intent);
                                }
                                else showDialogER("BẠN CHƯA CÓ THU, CHI VÀO THỜI GIAN NÀY");
                            }
                        }

                        @Override
                        public void onFailure(Call<InoutPerMonthResponse> call, Throwable t) {

                        }
                    });
                }
                else showDialogER("VUI LÒNG NHẬP THÁNG, NĂM");
            }
        });


        menu();
    }

    private void menu() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_thongke);

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

    private void showDialogER(String errorString)
    {
        final Button next;
        final TextView error;
        final Dialog dialog = new Dialog(InoutPerMonth.this);
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
