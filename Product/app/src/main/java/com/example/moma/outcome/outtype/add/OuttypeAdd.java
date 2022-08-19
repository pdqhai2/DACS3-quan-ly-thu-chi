package com.example.moma.outcome.outtype.add;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.moma.Home;
import com.example.moma.R;
import com.example.moma.RequestPack.outcome.type.add.OuttypeAddRequest;
import com.example.moma.ResponsePack.outcome.outtype.OuttypeListResponse;
import com.example.moma.RetrofitPack.RetrofitClient;
import com.example.moma.SaveData;
import com.example.moma.income.add.IncomeAddTypeList;
import com.example.moma.income.intype.list.IntypeList;
import com.example.moma.income.list.IncomeList;
import com.example.moma.outcome.add.OutcomeAddTypeList;
import com.example.moma.outcome.list.OutcomeList;
import com.example.moma.outcome.outtype.list.OuttypeList;
import com.example.moma.thongke.thongkeMain;
import com.example.moma.user.UserDetail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OuttypeAdd extends AppCompatActivity {

    SaveData saveData = new SaveData();

    EditText outtype_name;
    int user_id;

    OuttypeAddRequest outtypeAddRequest = new OuttypeAddRequest();
    OuttypeListResponse outtypeListResponse = new OuttypeListResponse();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outtype_add);

        user_id = saveData.getUser(getApplicationContext());

        Intent intent = getIntent();
        String mission = intent.getStringExtra("message_outtype_add");

        outtype_name = findViewById(R.id.outtype_add_name);

        findViewById(R.id.outtype_add_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), OuttypeList.class);
                startActivity(intent1);
                finish();
            }
        });

        findViewById(R.id.outtype_add_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(outtype_name.getText().toString().length() ==0)
                    Toast.makeText(getApplicationContext(), "Kiem tra lai thong tin!", Toast.LENGTH_SHORT).show();
                else {
                    outtypeAddRequest.setUser_id(user_id);
                    outtypeAddRequest.setOuttype_name(outtype_name.getText().toString());

                    Call<OuttypeListResponse> call = RetrofitClient.getRetrofitClient().addOuttype(outtypeAddRequest);
                    call.enqueue(new Callback<OuttypeListResponse>() {
                        @Override
                        public void onResponse(Call<OuttypeListResponse> call, Response<OuttypeListResponse> response) {
                            if (response.isSuccessful())
                            {
                                outtypeListResponse = response.body();
                                if (outtypeListResponse.isSuccess())
                                {
                                    if(mission.equals("outcome"))
                                    {
                                        Intent intent1 = new Intent(getApplicationContext(), OutcomeAddTypeList.class);
                                        startActivity(intent1);
                                        finish();
                                    }
                                    else
                                    {
                                        Intent intent1 = new Intent(getApplicationContext(), OuttypeList.class);
                                        startActivity(intent1);
                                        finish();
                                    }
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<OuttypeListResponse> call, Throwable t) {

                        }
                    });
                }
            }
        });

        menu();
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
