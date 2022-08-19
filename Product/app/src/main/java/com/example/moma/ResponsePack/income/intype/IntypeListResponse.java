package com.example.moma.ResponsePack.income.intype;


import com.example.moma.models.Intype;

import java.util.List;

public class IntypeListResponse {
    public boolean success;
    public List<Intype> intype;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Intype> getIntypes() {
        return intype;
    }

    public void setIntype(List<Intype> intype) {
        this.intype = intype;
    }

}
