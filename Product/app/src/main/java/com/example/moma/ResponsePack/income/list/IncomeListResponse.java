package com.example.moma.ResponsePack.income.list;

import com.example.moma.models.Income;

import java.util.List;

public class IncomeListResponse {
    public boolean success;
    public List<Income> income;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Income> getIncome() {
        return income;
    }

    public void setIncome(List<Income> income) {
        this.income = income;
    }
}
