package com.example.moma.outcome.list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moma.Home;
import com.example.moma.R;
import com.example.moma.RequestPack.Income.list.IncomeListRequest;
import com.example.moma.RequestPack.outcome.list.OutcomeListRequest;
import com.example.moma.ResponsePack.income.list.IncomeListResponse;
import com.example.moma.ResponsePack.outcome.list.OutcomeListResponse;
import com.example.moma.RetrofitPack.RetrofitClient;
import com.example.moma.SaveData;
import com.example.moma.income.add.IncomeAddTypeList;
import com.example.moma.income.intype.list.IntypeList;
import com.example.moma.income.list.IncomeAdapter;
import com.example.moma.income.list.IncomeList;
import com.example.moma.outcome.add.OutcomeAddTypeList;
import com.example.moma.outcome.outtype.list.OuttypeList;
import com.example.moma.thongke.thongkeMain;
import com.example.moma.user.UserDetail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutcomeList extends AppCompatActivity {

    private SaveData saveData = new SaveData();
    private RecyclerView outcomeRecyclerView;
    private OutcomeAdapter outcomeAdapter;

    public OutcomeListRequest outcomeListRequest = new OutcomeListRequest();
    public OutcomeListResponse outcomeListResponse = new OutcomeListResponse();

    public int user_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outcome_list);

        user_id = saveData.getUser(getApplicationContext());

        menu();

        outcomeRecyclerView = findViewById(R.id.outcome_rcv);
        outcomeAdapter = new OutcomeAdapter(this);
        LinearLayoutManager llm = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        outcomeRecyclerView.setLayoutManager(llm);
        getOutcomeData();
        outcomeRecyclerView.setAdapter(outcomeAdapter);

        findViewById(R.id.outcome_typeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OuttypeList.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.outcome_addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), OutcomeAddTypeList.class);
                startActivity(intent1);
            }
        });
    }

    public void getOutcomeData() {
        outcomeListRequest.setUser_id(user_id);

        Call<OutcomeListResponse> call = RetrofitClient.getRetrofitClient().getOutcome(outcomeListRequest);
        call.enqueue(new Callback<OutcomeListResponse>() {
            @Override
            public void onResponse(Call<OutcomeListResponse> call, Response<OutcomeListResponse> response) {
                if (response.isSuccessful()){
                    outcomeListResponse = response.body();
                    if(outcomeListResponse.isSuccess())
                    {
                        outcomeAdapter.setOutcomeList(outcomeListResponse.getOutcome());
                    }
                }
                else
                {
                    Toast.makeText(OutcomeList.this, "Fail call response!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OutcomeListResponse> call, Throwable t) {
                Log.d("ERROR", "onFailure: " + t.getLocalizedMessage());
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
}
