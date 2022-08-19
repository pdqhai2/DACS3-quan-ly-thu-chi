package com.example.moma;

import android.content.Context;
import android.content.SharedPreferences;

public class SaveData {
//    user
    public int getUser(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", context.MODE_PRIVATE);
        int user_id = sharedPreferences.getInt("user_id", 0);
        return user_id;
    }

    public void pushUser(Context context, int user_id)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("user_id", user_id).commit();
    }

    public void removeUser(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", context.MODE_PRIVATE);
        sharedPreferences.edit().remove("user_id").commit();
    }

//    check box
    public boolean getCheckBox(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("checkbox", context.MODE_PRIVATE);
        Boolean status = sharedPreferences.getBoolean("checked", false);
        return status;
    }

    public void pushCheckBox(Context context, boolean status)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("checkbox", context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("checked", status).commit();
    }

//    income
    public int getIncome(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("income_edit", context.MODE_PRIVATE);
        int income_id = sharedPreferences.getInt("income_id", 0);
        return income_id;
    }

    public void pushIncome(Context context, int id)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("income_edit", context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("income_id", id).commit();
    }

    public void removeIncome(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("income_edit", context.MODE_PRIVATE);
        sharedPreferences.edit().remove("income_id").commit();
    }

//    outcome
//    income
public int getOutcome(Context context)
{
    SharedPreferences sharedPreferences = context.getSharedPreferences("outcome_edit", context.MODE_PRIVATE);
    int outcome_id = sharedPreferences.getInt("outcome_id", 0);
    return outcome_id;
}

    public void pushOutcome(Context context, int id)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("outcome_edit", context.MODE_PRIVATE);
        sharedPreferences.edit().putInt("outcome_id", id).commit();
    }

    public void removeOutcome(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("outcome_edit", context.MODE_PRIVATE);
        sharedPreferences.edit().remove("outcome_id").commit();
    }
}
