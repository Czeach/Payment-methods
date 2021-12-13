package com.czech.payment_methods.network;

import com.czech.payment_methods.model.PaymentMethods;

public interface RepositoryCallback {

    void onSuccess(PaymentMethods data);

    void  onFailure(Throwable error);
}
