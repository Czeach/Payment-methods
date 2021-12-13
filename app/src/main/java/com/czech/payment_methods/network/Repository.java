package com.czech.payment_methods.network;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.czech.payment_methods.model.PaymentMethods;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class Repository {

    @Inject
    ApiService apiService;

    public Repository(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getMethods(RepositoryCallback request) {
        Call<PaymentMethods> call = apiService.Methods();
        call.enqueue(new Callback<PaymentMethods>() {
            @Override
            public void onResponse(@NonNull Call<PaymentMethods> call, @NonNull Response<PaymentMethods> response) {
                if (response.isSuccessful()) {
                    request.onSuccess(response.body());
                    Timber.d(response.message());
                    Timber.d(response.body().toString());
                } else  {
                    request.onFailure(new Exception("Error fetching payment methods"));
                    Timber.d(response.errorBody().toString());
                }
            }
            @Override
            public void onFailure(@NonNull Call<PaymentMethods> call, @NonNull Throwable t) {
                request.onFailure(t);
                Timber.d(t);
            }
        });
    }
}
