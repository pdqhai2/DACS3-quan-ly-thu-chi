package com.example.moma.thongke.year;

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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moma.Home;
import com.example.moma.R;
import com.example.moma.RequestPack.thongke.InoutPerYearRequest;
import com.example.moma.ResponsePack.thongke.InoutPerYearResponse;
import com.example.moma.RetrofitPack.RetrofitClient;
import com.example.moma.SaveData;
import com.example.moma.income.list.IncomeList;
import com.example.moma.outcome.list.OutcomeList;
import com.example.moma.thongke.month.InoutPerMonth;
import com.example.moma.thongke.thongkeMain;
import com.example.moma.user.UserDetail;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InoutPerYearDetail extends AppCompatActivity {
    int user_id;
    int in1 = 0;
    int in2 = 0;
    int in3 = 0;
    int in4  = 0;
    int in5  = 0;
    int in6  = 0;
    int in7  = 0;
    int in8  = 0;
    int in9 = 0;
    int in10 = 0;
    int in11 = 0;
    int in12 = 0;
    int out1 = 0;
    int out2 = 0;
    int  out3 = 0;
    int out4 = 0;
    int out5 = 0;
    int out6 = 0;
    int out7 = 0;
    int out8 = 0;
    int out9 = 0;
    int out10 = 0;
    int out11 = 0;
    int out12 = 0;
    String nam;
    SaveData saveData = new SaveData();

    InoutPerYearRequest request = new InoutPerYearRequest();
    InoutPerYearResponse perYearResponse = new InoutPerYearResponse();

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inout_per_year_detail);

        user_id = saveData.getUser(getApplicationContext());
        Intent intent = getIntent();
        nam = intent.getStringExtra("nam");

        barChart = findViewById(R.id.barchart);

        request.setUser_id(user_id);
        request.setYear(nam);

        Call<InoutPerYearResponse> call = RetrofitClient.getRetrofitClient().inoutPerYear(request);
        call.enqueue(new Callback<InoutPerYearResponse>() {
            @Override
            public void onResponse(Call<InoutPerYearResponse> call, Response<InoutPerYearResponse> response) {
                if (response.isSuccessful())
                {
                    perYearResponse = response.body();
                    if (perYearResponse.getIncome1() != null) in1 = perYearResponse.getIncome1().getSi_val()*1000 + perYearResponse.getIncome1().getSi_val_2();
                    if (perYearResponse.getIncome2() != null) in2 = perYearResponse.getIncome2().getSi_val()*1000 + perYearResponse.getIncome2().getSi_val_2();
                    if (perYearResponse.getIncome3() != null) in3 = perYearResponse.getIncome3().getSi_val()*1000 + perYearResponse.getIncome3().getSi_val_2();
                    if (perYearResponse.getIncome4() != null) in4 = perYearResponse.getIncome4().getSi_val()*1000 + perYearResponse.getIncome4().getSi_val_2();
                    if (perYearResponse.getIncome5() != null) in5 = perYearResponse.getIncome5().getSi_val()*1000 + perYearResponse.getIncome5().getSi_val_2();
                    if (perYearResponse.getIncome6() != null) in6 = perYearResponse.getIncome6().getSi_val()*1000 + perYearResponse.getIncome6().getSi_val_2();
                    if (perYearResponse.getIncome7() != null) in7 = perYearResponse.getIncome7().getSi_val()*1000 + perYearResponse.getIncome7().getSi_val_2();
                    if (perYearResponse.getIncome8() != null) in8 = perYearResponse.getIncome8().getSi_val()*1000 + perYearResponse.getIncome8().getSi_val_2();
                    if (perYearResponse.getIncome9() != null) in9 = perYearResponse.getIncome9().getSi_val()*1000 + perYearResponse.getIncome9().getSi_val_2();
                    if (perYearResponse.getIncome10() != null) in10 = perYearResponse.getIncome10().getSi_val()*1000 + perYearResponse.getIncome10().getSi_val_2();
                    if (perYearResponse.getIncome11() != null) in11 = perYearResponse.getIncome11().getSi_val()*1000 + perYearResponse.getIncome11().getSi_val_2();
                    if (perYearResponse.getIncome12() != null) in12 = perYearResponse.getIncome12().getSi_val()*1000 + perYearResponse.getIncome12().getSi_val_2();

                    if (perYearResponse.getOutcome1() != null) out1 = perYearResponse.getOutcome1().getSo_val()*1000 + perYearResponse.getOutcome1().getSo_val_2();
                    if (perYearResponse.getOutcome2() != null) out2 = perYearResponse.getOutcome2().getSo_val()*1000 + perYearResponse.getOutcome2().getSo_val_2();
                    if (perYearResponse.getOutcome3() != null) out3 = perYearResponse.getOutcome3().getSo_val()*1000 + perYearResponse.getOutcome3().getSo_val_2();
                    if (perYearResponse.getOutcome4() != null) out4 = perYearResponse.getOutcome4().getSo_val()*1000 + perYearResponse.getOutcome4().getSo_val_2();
                    if (perYearResponse.getOutcome5() != null) out5 = perYearResponse.getOutcome5().getSo_val()*1000 + perYearResponse.getOutcome5().getSo_val_2();
                    if (perYearResponse.getOutcome6() != null) out6 = perYearResponse.getOutcome6().getSo_val()*1000 + perYearResponse.getOutcome6().getSo_val_2();
                    if (perYearResponse.getOutcome7() != null) out7 = perYearResponse.getOutcome7().getSo_val()*1000 + perYearResponse.getOutcome7().getSo_val_2();
                    if (perYearResponse.getOutcome8() != null) out8 = perYearResponse.getOutcome8().getSo_val()*1000 + perYearResponse.getOutcome8().getSo_val_2();
                    if (perYearResponse.getOutcome9() != null) out9 = perYearResponse.getOutcome9().getSo_val()*1000 + perYearResponse.getOutcome9().getSo_val_2();
                    if (perYearResponse.getOutcome10() != null) out10 = perYearResponse.getOutcome10().getSo_val()*1000 + perYearResponse.getOutcome10().getSo_val_2();
                    if (perYearResponse.getOutcome11() != null) out11 = perYearResponse.getOutcome11().getSo_val()*1000 + perYearResponse.getOutcome11().getSo_val_2();
                    if (perYearResponse.getOutcome12() != null) out12 = perYearResponse.getOutcome12().getSo_val()*1000 + perYearResponse.getOutcome12().getSo_val_2();

                    showDialogER(String.valueOf(in5));
                }
            }

            @Override
            public void onFailure(Call<InoutPerYearResponse> call, Throwable t) {

            }
        });

        BarDataSet income = new BarDataSet(income(), "Thu nhập");
        income.setColor(Color.GREEN);

        BarDataSet outcome = new BarDataSet(outcome(), "Chi tiêu");
        outcome.setColor(Color.RED);

        BarData barData = new BarData(income, outcome);
        barChart.setData(barData);

        String[] months = new String[] {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4","Tháng 5", "Tháng 6", "Tháng 7", "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(months));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.getGranularity();
        xAxis.setGranularityEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(2);

        float barSpace = 0.1f;
        float groupSpace = 0.5f;

        barData.setBarWidth(0.15f);
        barChart.getXAxis().getAxisMinimum();
//        barChart.getXAxis().getAxisMaximum(0+barChart.getBarData().getGroupWidth(groupSpace, barSpace)*12);
        barChart.groupBars(0, groupSpace, barSpace);
        barChart.invalidate();

        menu();
    }

    private ArrayList<BarEntry> income()
    {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, in1));
        barEntries.add(new BarEntry(2, in2));
        barEntries.add(new BarEntry(3, in3));
        barEntries.add(new BarEntry(4, in4));
        barEntries.add(new BarEntry(5, in5));
        barEntries.add(new BarEntry(6, in6));
        barEntries.add(new BarEntry(7, in7));
        barEntries.add(new BarEntry(8, in8));
        barEntries.add(new BarEntry(9, in9));
        barEntries.add(new BarEntry(10, in10));
        barEntries.add(new BarEntry(11, in11));
        barEntries.add(new BarEntry(12, in12));
        return barEntries;
    }

    private ArrayList<BarEntry> outcome()
    {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, out1));
        barEntries.add(new BarEntry(2, out2));
        barEntries.add(new BarEntry(3, out3));
        barEntries.add(new BarEntry(4, out4));
        barEntries.add(new BarEntry(5, out5));
        barEntries.add(new BarEntry(6, out6));
        barEntries.add(new BarEntry(7, out7));
        barEntries.add(new BarEntry(8, out8));
        barEntries.add(new BarEntry(9, out9));
        barEntries.add(new BarEntry(10, out10));
        barEntries.add(new BarEntry(11, out11));
        barEntries.add(new BarEntry(12, out12));
        return barEntries;
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
        final Dialog dialog = new Dialog(InoutPerYearDetail.this);
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
