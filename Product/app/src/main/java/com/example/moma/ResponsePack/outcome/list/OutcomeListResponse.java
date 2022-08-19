package com.example.moma.ResponsePack.outcome.list;

import com.example.moma.models.Outcome;

import java.util.List;

public class OutcomeListResponse {
    boolean success;
    List<Outcome> outcome;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Outcome> getOutcome() {
        return outcome;
    }

    public void setOutcome(List<Outcome> outcome) {
        this.outcome = outcome;
    }
}
