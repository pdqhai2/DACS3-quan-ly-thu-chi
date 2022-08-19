package com.example.moma.ResponsePack.outcome.detail;

import com.example.moma.models.Outcome;
import com.example.moma.models.Outtype;

public class OutcomeDetailResponse {
    public boolean success;
    public Outtype outtype;
    public Outcome outcome;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Outtype getOuttype() {
        return outtype;
    }

    public void setOuttype(Outtype outtype) {
        this.outtype = outtype;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }
}
