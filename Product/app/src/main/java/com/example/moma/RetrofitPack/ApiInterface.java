package com.example.moma.RetrofitPack;

import com.example.moma.RequestPack.Income.del.IncomeDelRequest;
import com.example.moma.RequestPack.Income.intype.add.IntypeAddRequest;
import com.example.moma.RequestPack.Income.list.IncomeListByIntypeRequest;
import com.example.moma.RequestPack.User.detail.UserDetailRequest;
import com.example.moma.RequestPack.User.login.LoginRequest;
import com.example.moma.RequestPack.Income.add.IncomeAddRequest;
import com.example.moma.RequestPack.Income.detail.IncomeDetailRequest;
import com.example.moma.RequestPack.Income.intype.list.IntypeListRequest;
import com.example.moma.RequestPack.Income.list.IncomeListRequest;
import com.example.moma.RequestPack.outcome.add.OutcomeAddRequest;
import com.example.moma.RequestPack.outcome.del.OutcomeDelRequest;
import com.example.moma.RequestPack.outcome.detail.OutcomeDetailRequest;
import com.example.moma.RequestPack.outcome.list.OutcomeListRequest;
import com.example.moma.RequestPack.outcome.type.add.OuttypeAddRequest;
import com.example.moma.RequestPack.outcome.type.list.OuttypeListRequest;
import com.example.moma.RequestPack.thongke.InoutPerMonthRequest;
import com.example.moma.RequestPack.thongke.InoutPerYearRequest;
import com.example.moma.ResponsePack.User.login.LoginResponse;
import com.example.moma.ResponsePack.income.detail.IncomeDetailResponse;
import com.example.moma.ResponsePack.income.list.IncomeListResponse;
import com.example.moma.ResponsePack.income.intype.IntypeListResponse;
import com.example.moma.ResponsePack.outcome.detail.OutcomeDetailResponse;
import com.example.moma.ResponsePack.outcome.list.OutcomeListResponse;
import com.example.moma.ResponsePack.outcome.outtype.OuttypeListResponse;
import com.example.moma.ResponsePack.thongke.InoutPerYearResponse;
import com.example.moma.ResponsePack.thongke.InoutPerMonthResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

//    user
    @POST("getUser")
    Call<LoginResponse> getUser(@Body LoginRequest loginRequest);

    @POST("register")
    Call<LoginResponse> register(@Body LoginRequest regisRequest);

    @POST("getUserDetail")
    Call<LoginResponse> getUserDetail(@Body UserDetailRequest userDetailRequest);

//    income
    @POST("getIncome")
    Call<IncomeListResponse> getIncome(@Body IncomeListRequest incomeListRequest);

    @POST("getIncomeByType")
    Call<IncomeListResponse> getIncomeByType(@Body IncomeListByIntypeRequest incomeListRequest);

    @POST("addIncome")
    Call<IncomeListResponse> addIncome(@Body IncomeAddRequest incomeAddRequest);

    @POST("editIncome")
    Call<IncomeDetailResponse> editIncome(@Body IncomeAddRequest incomeAddRequest);

    @POST("getIncomeDetail")
    Call<IncomeDetailResponse> getIncomeDetail(@Body IncomeDetailRequest incomeDetailRequest);

    @POST("getIncomeDetailEdit")
    Call<IncomeDetailResponse> getIncomeDetailEdit(@Body IncomeDetailRequest incomeDetailRequest);

    @POST("delIncome")
    Call<IncomeListResponse> delIncome(@Body IncomeDelRequest incomeDelRequest);

    @POST("getIntype")
    Call<IntypeListResponse> getIntype(@Body IntypeListRequest intypeListRequest);

    @POST("addIntype")
    Call<IntypeListResponse> addIntype(@Body IntypeAddRequest intypeAddRequest);

//    outcome
    @POST("getOutcome")
    Call<OutcomeListResponse> getOutcome(@Body OutcomeListRequest outcomeListRequest);

    @POST("addOutcome")
    Call<OutcomeListResponse> addOutcome(@Body OutcomeAddRequest outcomeAddRequest);

    @POST("getOutcomeDetail")
    Call<OutcomeDetailResponse> getOutcomeDetail(@Body OutcomeDetailRequest incomeDetailRequest);

    @POST("delOutcome")
    Call<OutcomeListResponse> delOutcome(@Body OutcomeDelRequest incomeDelRequest);

    @POST("getOuttype")
    Call<OuttypeListResponse> getOuttype(@Body OuttypeListRequest outtypeListRequest);

    @POST("addOuttype")
    Call<OuttypeListResponse> addOuttype(@Body OuttypeAddRequest outtypeAddRequest);

//    thong ke
    @POST("inoutPerMonth")
    Call<InoutPerMonthResponse> inoutPerMonth(@Body InoutPerMonthRequest inoutPerMonthRequest);

    @POST("inoutPerYear")
    Call<InoutPerYearResponse> inoutPerYear(@Body InoutPerYearRequest inoutPerMonthRequest);
}
