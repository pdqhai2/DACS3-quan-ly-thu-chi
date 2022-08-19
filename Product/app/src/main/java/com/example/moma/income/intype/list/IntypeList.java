package com.example.moma.income.intype.list;

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
import com.example.moma.RequestPack.Income.intype.list.IntypeListRequest;
import com.example.moma.ResponsePack.income.intype.IntypeListResponse;
import com.example.moma.RetrofitPack.RetrofitClient;
import com.example.moma.SaveData;
import com.example.moma.income.intype.add.IntypeAdd;
import com.example.moma.income.list.IncomeList;
import com.example.moma.outcome.list.OutcomeList;
import com.example.moma.thongke.thongkeMain;
import com.example.moma.user.UserDetail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IntypeList extends AppCompatActivity {

    private RecyclerView intypeRecyclerView;
    private IntypeAdapter intypeAdapter;

    public IntypeListRequest intypeListRequest;
    public IntypeListResponse intypeListResponse;

    public int user_id;

    SaveData saveData = new SaveData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intype_list);

        menu();

        user_id = saveData.getUser(getApplicationContext());

        intypeRecyclerView = findViewById(R.id.intype_rcv);
        intypeAdapter = new IntypeAdapter(this);
        LinearLayoutManager llm = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        intypeRecyclerView.setLayoutManager(llm);
        getIntypeData();
        intypeRecyclerView.setAdapter(intypeAdapter);

        findViewById(R.id.intype_incomeBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(IntypeList.this, IncomeList.class);
                startActivity(intent1);
            }
        });

        findViewById(R.id.intype_addBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), IntypeAdd.class);
                intent.putExtra("message_intype_add", "intype");
                startActivity(intent);
            }
        });
    }

    private void getIntypeData() {
        intypeListRequest = new IntypeListRequest();
        intypeListRequest.setUser_id(user_id);

        Call<IntypeListResponse> call = RetrofitClient.getRetrofitClient().getIntype(intypeListRequest);
        call.enqueue(new Callback<IntypeListResponse>() {
            @Override
            public void onResponse(Call<IntypeListResponse> call, Response<IntypeListResponse> response) {
                if (response.isSuccessful()){
                    intypeListResponse = response.body();
                    if(intypeListResponse.isSuccess())
                    {
                        intypeAdapter.setData(intypeListResponse.getIntypes());
                    }
                }
                else
                {
                    Toast.makeText(IntypeList.this, "Fail call response!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<IntypeListResponse> call, Throwable t) {
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
}
