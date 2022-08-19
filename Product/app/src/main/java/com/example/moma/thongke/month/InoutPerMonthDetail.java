package com.example.moma.thongke.month;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moma.Home;
import com.example.moma.R;
import com.example.moma.RequestPack.thongke.InoutPerMonthRequest;
import com.example.moma.ResponsePack.thongke.InoutPerYearResponse;
import com.example.moma.SaveData;
import com.example.moma.income.list.IncomeAdapter;
import com.example.moma.income.list.IncomeList;
import com.example.moma.models.Income;
import com.example.moma.models.Outcome;
import com.example.moma.outcome.list.OutcomeAdapter;
import com.example.moma.outcome.list.OutcomeList;
import com.example.moma.thongke.thongkeMain;
import com.example.moma.user.UserDetail;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class InoutPerMonthDetail extends AppCompatActivity {

    private PieChart pieChart;
    int user_id, sumin, sumout;

    String thang, nam;

    TextView tvthangnam, thu, chi;

    SaveData saveData = new SaveData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inout_per_month_detail);

        user_id = saveData.getUser(getApplicationContext());
        Intent intent = getIntent();
        sumin = intent.getIntExtra("sumin", 0);
        sumout = intent.getIntExtra("sumout", 0);
        thang = intent.getStringExtra("thang");
        nam = intent.getStringExtra("nam");

        tvthangnam = findViewById(R.id.tvmonthyear);
        tvthangnam.setText("Tháng " + thang + " năm " + nam);
        thu = findViewById(R.id.tvin);
        chi = findViewById(R.id.tvout);

        thu.setText(formatMoney(String.valueOf(sumin)) + " VND");
        chi.setText(formatMoney(String.valueOf(sumout)) + " VND");

        pieChart = findViewById(R.id.inout_piechart);

        setupPieChart();
        loadPieChartData();

        menu();
    }

    private String formatMoney(String number)
    {
        DecimalFormat decimalFormat = new DecimalFormat("###,###,##0");
        return decimalFormat.format(Double.parseDouble(number));
    }

    private void setupPieChart() {
        pieChart.setDrawHoleEnabled(false);
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(30);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setEnabled(false);
    }

    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(sumin, "Thu nhập"));
        entries.add(new PieEntry(sumout, "Chi tiêu"));

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS) { colors.add(color); }
        for (int color: ColorTemplate.VORDIPLOM_COLORS){ colors.add(color); }

        PieDataSet pieDataSet = new PieDataSet(entries,"");
        pieDataSet.setColors(colors);

        PieData data = new PieData(pieDataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(25);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();
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
}
