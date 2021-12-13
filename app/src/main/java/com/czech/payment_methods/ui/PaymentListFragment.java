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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPaymentListBinding.inflate(getLayoutInflater());

        swipeRefreshLayout = binding.swipeToRefreshLayout;

        viewModel = new ViewModelProvider(requireActivity()).get(PaymentListViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        progressBar = binding.progressBar;

        initRecyclerView();
        observeViewModel();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.setAdapter(null);

                progressBar.setVisibility(View.VISIBLE);

                initRecyclerView();
                observeViewModel();

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    void initRecyclerView() {

        recyclerView = binding.paymentMethodsRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        paymentMethodAdapter = new PaymentMethodAdapter();
        recyclerView.setAdapter(paymentMethodAdapter);
    }

    void observeViewModel() {
        progressBar.setVisibility(View.VISIBLE);
        viewModel.makeCall();
        viewModel.getResponse().observe(getViewLifecycleOwner(), new Observer<PaymentMethods>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(PaymentMethods networks) {
                if (networks != null) {
                    progressBar.setVisibility(View.GONE);

                    paymentMethodAdapter.setListItems(networks.getNetworks().getApplicable());
                    paymentMethodAdapter.notifyDataSetChanged();
                } else  {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(requireActivity(), "Error in getting data", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}