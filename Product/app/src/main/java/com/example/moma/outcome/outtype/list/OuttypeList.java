package com.example.moma.outcome.outtype.list;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.moma.ResponsePack.outcome.outtype.OuttypeListResponse;
import com.example.moma.RetrofitPack.RetrofitClient;
import com.example.moma.SaveData;
import com.example.moma.income.intype.add.IntypeAdd;
import com.example.moma.income.intype.list.IntypeAdapter;
import com.example.moma.income.intype.list.IntypeList;
import com.example.moma.income.list.IncomeList;
import com.example.moma.outcome.list.OutcomeList;
import com.example.moma.outcome.outtype.add.OuttypeAdd;
import com.example.moma.thongke.thongkeMain;
import com.example.moma.user.UserDetail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuttypeList extends AppCompatActivity {

    private RecyclerView outtypeRecyclerView;
    private OuttypeAdapter outtypeAdapter;

    public int user_id;

    SaveData saveData = new SaveData();

    OuttypeListRequest outtypeListRequest = new OuttypeListRequest();
    OuttypeListResponse outtypeListResponse = new OuttypeListResponse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outtype_list);

        user_id = saveData.getUser(getApplicationContext());

        outtypeRecyclerView = findViewById(R.id.outtype_rcv);
        outtypeAdapter = new OuttypeAdapter(this);
        LinearLayoutManager llm = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        outtypeRecyclerView.setLayoutManager(llm);
        getOuttypeData();
        outtypeRecyclerView.setAdapter(outtypeAdapter);

        findViewById(R.id.outtype_outcomeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(OuttypeList.this, OutcomeList.class);
                startActivity(intent1);
            }
        });

        findViewById(R.id.outtype_addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OuttypeAdd.class);
                intent.putExtra("message_outtype_add", "outtype");
                startActivity(intent);
            }
        });

        menu();
    }

    private void getOuttypeData() {
        outtypeListRequest.setUser_id(user_id);

        Call<OuttypeListResponse> call = RetrofitClient.getRetrofitClient().getOuttype(outtypeListRequest);
        call.enqueue(new Callback<OuttypeListResponse>() {
            @Override
            public void onResponse(Call<OuttypeListResponse> call, Response<OuttypeListResponse> response) {
                if (response.isSuccessful())
                {
                    outtypeListResponse = response.body();
                    if (outtypeListResponse.isSuccess())
                    {
                        outtypeAdapter.setOuttypeList(outtypeListResponse.getOuttype());
                    }
                }
            }

            @Override
            public void onFailure(Call<OuttypeListResponse> call, Throwable t) {

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
