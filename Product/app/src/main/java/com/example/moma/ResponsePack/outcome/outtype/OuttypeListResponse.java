package com.example.moma.ResponsePack.outcome.outtype;

import com.example.moma.models.Outtype;

import java.util.List;

public class OuttypeListResponse {
    public boolean success;
    public List<Outtype> outtype;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Outtype> getOuttype() {
        return outtype;
    }

    public void setOuttype(List<Outtype> outtype) {
        this.outtype = outtype;
    }
}
