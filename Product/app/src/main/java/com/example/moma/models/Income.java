package com.example.moma.models;

public class Income {
    public int income_id, user_id, intype_id;
    int income_val, income_val_2;
    public String income_name, income_valstring, income_day, income_month, income_year;

    public int getIncome_id() {
        return income_id;
    }

    public void setIncome_id(int income_id) {
        this.income_id = income_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getIntype_id() {
        return intype_id;
    }

    public void setIntype_id(int intype_id) {
        this.intype_id = intype_id;
    }

    public int getIncome_val() {
        return income_val;
    }

    public void setIncome_val(int income_val) {
        this.income_val = income_val;
    }

    public int getIncome_val_2() {
        return income_val_2;
    }

    public void setIncome_val_2(int income_val_2) {
        this.income_val_2 = income_val_2;
    }

    public String getIncome_name() {
        return income_name;
    }

    public String getIncome_valstring() {
        return income_valstring;
    }

    public void setIncome_valstring(String income_valstring) {
        this.income_valstring = income_valstring;
    }

    public void setIncome_name(String income_name) {
        this.income_name = income_name;
    }

    public String getIncome_day() {
        return income_day;
    }

    public void setIncome_day(String income_day) {
        this.income_day = income_day;
    }

    public String getIncome_month() {
        return income_month;
    }

    public void setIncome_month(String income_month) {
        this.income_month = income_month;
    }

    public String getIncome_year() {
        return income_year;
    }

    public void setIncome_year(String income_year) {
        this.income_year = income_year;
    }
}
