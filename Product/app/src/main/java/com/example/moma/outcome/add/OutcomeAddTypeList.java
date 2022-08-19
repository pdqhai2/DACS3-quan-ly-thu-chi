package com.example.moma.outcome.add;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moma.Home;
import com.example.moma.R;
import com.example.moma.RequestPack.Income.intype.list.IntypeListRequest;
import com.example.moma.RequestPack.outcome.type.list.OuttypeListRequest;
import com.example.moma.ResponsePack.income.intype.IntypeListResponse;
import com.example.moma.ResponsePack.outcome.outtype.OuttypeListResponse;
import com.example.moma.RetrofitPack.RetrofitClient;
import com.example.moma.SaveData;
import com.example.moma.income.add.IncomeIntypeAdapter;
import com.example.moma.income.intype.add.IntypeAdd;
import com.example.moma.income.list.IncomeList;
import com.example.moma.outcome.list.OutcomeList;
import com.example.moma.outcome.outtype.add.OuttypeAdd;
import com.example.moma.thongke.thongkeMain;
import com.example.moma.user.UserDetail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OutcomeAddTypeList extends AppCompatActivity {
    private RecyclerView outtypeRecyclerView;
    private OutcomeOuttypeAdater outcomeOuttypeAdater;

    public OuttypeListRequest outtypeListRequest;
    public OuttypeListResponse outtypeListResponse;

    public int user_id;

    SaveData saveData = new SaveData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outcome_add_type);

        user_id = saveData.getUser(getApplicationContext());

        menu();

        outtypeRecyclerView = findViewById(R.id.outcome_add_type_rcv);
        outcomeOuttypeAdater = new OutcomeOuttypeAdater(this);
        LinearLayoutManager llm = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        outtypeRecyclerView.setLayoutManager(llm);
        getOuttypeData();
        outtypeRecyclerView.setAdapter(outcomeOuttypeAdater);

        findViewById(R.id.outcome_add_newType).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message_outtype_add = "outcome";
                Intent intent = new Intent(getApplicationContext(), OuttypeAdd.class);
                intent.putExtra("message_outtype_add", message_outtype_add);
                startActivity(intent);
            }
        });

        findViewById(R.id.outcome_add_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OutcomeList.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void getOuttypeData() {
        outtypeListRequest = new OuttypeListRequest();
        outtypeListRequest.setUser_id(user_id);

        Call<OuttypeListResponse> call = RetrofitClient.getRetrofitClient().getOuttype(outtypeListRequest);
        call.enqueue(new Callback<OuttypeListResponse>() {
            @Override
            public void onResponse(Call<OuttypeListResponse> call, Response<OuttypeListResponse> response) {
                if (response.isSuccessful()){
                    outtypeListResponse = response.body();
                    if(outtypeListResponse.isSuccess())
                    {
                        outcomeOuttypeAdater.setOuttypeList(outtypeListResponse.getOuttype());
                    }
                }
            }

            @Override
            public void onFailure(Call<OuttypeListResponse> call, Throwable t) {
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
