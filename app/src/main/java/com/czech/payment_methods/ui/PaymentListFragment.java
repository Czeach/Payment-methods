package com.czech.payment_methods.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.czech.payment_methods.adapter.PaymentMethodAdapter;
import com.czech.viewModel.PaymentListViewModel;
import com.czech.payment_methods.databinding.FragmentPaymentListBinding;
import com.czech.payment_methods.model.PaymentMethods;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PaymentListFragment extends Fragment {

    FragmentPaymentListBinding binding;
    PaymentListViewModel viewModel;
    PaymentMethodAdapter paymentMethodAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPaymentListBinding.inflate(getLayoutInflater());

        viewModel = new ViewModelProvider(requireActivity()).get(PaymentListViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initRecyclerView();
        observeViewModel();
    }

    void initRecyclerView() {
        RecyclerView recyclerView = binding.paymentMethodsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        paymentMethodAdapter = new PaymentMethodAdapter();
        recyclerView.setAdapter(paymentMethodAdapter);
    }

    void observeViewModel() {
        viewModel.makeCall();
        viewModel.getResponse().observe(getViewLifecycleOwner(), new Observer<PaymentMethods>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(PaymentMethods networks) {
                if (networks != null) {
                    paymentMethodAdapter.setListItems(networks.getNetworks().getApplicable());
                    paymentMethodAdapter.notifyDataSetChanged();
                } else  {
                    Toast.makeText(requireActivity(), "Error in getting data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}