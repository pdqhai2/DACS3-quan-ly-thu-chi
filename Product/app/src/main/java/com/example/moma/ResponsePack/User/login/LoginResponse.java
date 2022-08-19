package com.example.moma.ResponsePack.User.login;

import com.example.moma.models.User;

public class LoginResponse {
    public boolean success;
    public User user;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
