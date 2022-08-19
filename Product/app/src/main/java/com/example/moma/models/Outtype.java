package com.example.moma.models;

public class Outtype {
    public int outtype_id, user_id;
    public String outtype_name;

    public int getOuttype_id() {
        return outtype_id;
    }

    public void setOuttype_id(int outtype_id) {
        this.outtype_id = outtype_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getOuttype_name() {
        return outtype_name;
    }

    public void setOuttype_name(String outtype_name) {
        this.outtype_name = outtype_name;
    }
}
