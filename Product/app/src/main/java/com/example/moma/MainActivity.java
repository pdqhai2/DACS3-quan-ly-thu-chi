package com.example.moma;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private int DELAY_TIME = 2000;
    SaveData saveData = new SaveData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(saveData.getCheckBox(getApplicationContext()) && saveData.getUser(getApplicationContext()) != 0 )
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent signinIntent = new Intent(MainActivity.this, Home.class);
                    startActivity(signinIntent);
                    finish();
                }
            },DELAY_TIME);
        }
        else
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent signinIntent = new Intent(MainActivity.this, LogIn.class);
                    startActivity(signinIntent);
                    finish();
                }
            },DELAY_TIME);
        }
    }
}
