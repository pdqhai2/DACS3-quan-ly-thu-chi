package com.example.moma.models;

public class Intype {
    public int intype_id, user_id;
    public String intype_name;

    public Intype(int intype_id, int user_id, String intype_name) {
        this.intype_id = intype_id;
        this.user_id = user_id;
        this.intype_name = intype_name;
    }

    public int getIntype_id() {
        return intype_id;
    }

    public void setIntype_id(int intype_id) {
        this.intype_id = intype_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getIntype_name() {
        return intype_name;
    }

    public void setIntype_name(String intype_name) {
        this.intype_name = intype_name;
    }
}
