package com.example.moma.ResponsePack.thongke;

import com.example.moma.models.Sumincome;
import com.example.moma.models.Sumoutcome;

public class InoutPerMonthResponse {
    boolean success;
    Sumincome sumincome;
    Sumoutcome sumoutcome;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Sumincome getSumincome() {
        return sumincome;
    }

    public void setSumincome(Sumincome sumincome) {
        this.sumincome = sumincome;
    }

    public Sumoutcome getSumoutcome() {
        return sumoutcome;
    }

    public void setSumoutcome(Sumoutcome sumoutcome) {
        this.sumoutcome = sumoutcome;
    }
}
