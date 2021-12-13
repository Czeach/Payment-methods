package com.czech.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.czech.payment_methods.model.PaymentMethods;
import com.czech.payment_methods.network.ApiService;
import com.czech.payment_methods.network.Repository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PaymentListViewModel extends ViewModel {

    MutableLiveData<PaymentMethods> response;

    @Inject
    ApiService apiService;

    @Inject
    PaymentListViewModel() {
        response = new MutableLiveData<>();
    }

    public MutableLiveData<PaymentMethods> getResponse() {
        return response;
    }

    public void makeCall() {
        Repository repository = new Repository(apiService);
        repository.getMethods(response);
    }
}
