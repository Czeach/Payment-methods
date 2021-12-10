package com.czech.payment_methods.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.czech.payment_methods.databinding.FragmentPaymentListBinding;

public class PaymentListFragment extends Fragment {

    FragmentPaymentListBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPaymentListBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}