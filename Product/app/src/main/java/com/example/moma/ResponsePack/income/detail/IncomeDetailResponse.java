package com.example.moma.ResponsePack.income.detail;

import com.example.moma.models.Income;
import com.example.moma.models.Intype;

public class IncomeDetailResponse {
    public boolean success;
    public Income income;
    public Intype intype;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public Intype getIntype() {
        return intype;
    }

    public void setIntype(Intype intype) {
        this.intype = intype;
    }
}
