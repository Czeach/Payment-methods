package com.czech.payment_methods.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czech.payment_methods.PaymentListViewModel;
import com.czech.payment_methods.databinding.FragmentPaymentListBinding;
import com.czech.payment_methods.model.PaymentMethods;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PaymentListFragment extends Fragment {

    FragmentPaymentListBinding binding;
    PaymentListViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPaymentListBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(requireActivity()).get(PaymentListViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.makeCall();
        viewModel.getResponse().observe(getViewLifecycleOwner(), new Observer<PaymentMethods>() {
            @Override
            public void onChanged(PaymentMethods networks) {
                if (networks != null) {
                    binding.test.setText(networks.getNetworks().getApplicable().toString());
                } else  {
                    binding.test.setText("Error somewhere");
                }
            }
        });
    }
}