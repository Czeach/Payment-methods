package com.czech.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.czech.payment_methods.model.PaymentMethods;
import com.czech.payment_methods.network.ApiService;
import com.czech.payment_methods.network.Repository;
import com.czech.payment_methods.network.RepositoryCallback;

import java.util.Timer;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import timber.log.Timber;

@HiltViewModel
public class PaymentListViewModel extends ViewModel {

    MutableLiveData<PaymentMethods> response;

    @Inject
    Repository repository;

    @Inject
    PaymentListViewModel() {
        response = new MutableLiveData<>();
    }

    public MutableLiveData<PaymentMethods> getResponse() {
        return response;
    }

    public void makeCall() {
        repository.getMethods(new RepositoryCallback() {
            @Override
            public void onSuccess(PaymentMethods data) {
                response.setValue(data);
            }

            @Override
            public void onFailure(Throwable error) {
                Timber.e(error);
            }
        });
    }
}
