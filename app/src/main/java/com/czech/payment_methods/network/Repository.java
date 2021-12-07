package com.czech.payment_methods.network;

import androidx.lifecycle.MutableLiveData;

import com.czech.payment_methods.model.PaymentMethods;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private ApiService apiService;

    public Repository(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getMethods(MutableLiveData<PaymentMethods> liveData) {
        Call<PaymentMethods> call = apiService.Methods();
        call.enqueue(new Callback<PaymentMethods>() {
            @Override
            public void onResponse(Call<PaymentMethods> call, Response<PaymentMethods> response) {
                if (response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else  {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<PaymentMethods> call, Throwable t) {
                liveData.postValue(null);
            }
        });
    }
}
