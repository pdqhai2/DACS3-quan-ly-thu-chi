package com.example.moma.models;

public class Outcome {
    public int outcome_id, outtype_id, outcome_val, outcome_val_2;
    public String outcome_name, outcome_valstring, outcome_day, outcome_month, outcome_year;

    public int getOutcome_id() {
        return outcome_id;
    }

    public void setOutcome_id(int outcome_id) {
        this.outcome_id = outcome_id;
    }

    public int getOuttype_id() {
        return outtype_id;
    }

    public void setOuttype_id(int outtype_id) {
        this.outtype_id = outtype_id;
    }

    public int getOutcome_val() {
        return outcome_val;
    }

    public void setOutcome_val(int outcome_val) {
        this.outcome_val = outcome_val;
    }

    public int getOutcome_val_2() {
        return outcome_val_2;
    }

    public void setOutcome_val_2(int outcome_val_2) {
        this.outcome_val_2 = outcome_val_2;
    }

    public String getOutcome_name() {
        return outcome_name;
    }

    public void setOutcome_name(String outcome_name) {
        this.outcome_name = outcome_name;
    }

    public String getOutcome_valstring() {
        return outcome_valstring;
    }

    public void setOutcome_valstring(String outcome_valstring) {
        this.outcome_valstring = outcome_valstring;
    }

    public String getOutcome_day() {
        return outcome_day;
    }

    public void setOutcome_day(String outcome_day) {
        this.outcome_day = outcome_day;
    }

    public String getOutcome_month() {
        return outcome_month;
    }

    public void setOutcome_month(String outcome_month) {
        this.outcome_month = outcome_month;
    }

    public String getOutcome_year() {
        return outcome_year;
    }

    public void setOutcome_year(String outcome_year) {
        this.outcome_year = outcome_year;
    }
}
