package com.czech.payment_methods.network;

import com.czech.payment_methods.model.PaymentMethods;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("listresult.json")
    Call<PaymentMethods> Methods();
}
