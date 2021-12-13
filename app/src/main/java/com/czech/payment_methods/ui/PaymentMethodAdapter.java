package com.czech.payment_methods.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.czech.payment_methods.R;
import com.czech.payment_methods.model.ApplicableItem;

import java.util.List;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder> {

    private List<ApplicableItem> listItems;

    public void setListItems(List<ApplicableItem> listItems) {
        this.listItems = listItems;
    }

    @NonNull
    @Override
    public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new PaymentMethodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentMethodViewHolder holder, int position) {

        holder.label.setText(listItems.get(position).getLabel());
        holder.method.setText(listItems.get(position).getMethod());
        Glide.with(holder.logo)
                .load(listItems.get(position).getLinks().getLogo())
                .into(holder.logo);

    }

    @Override
    public int getItemCount() {
        if (listItems == null) {
            return 0;
        } else  {
            return listItems.size();
        }

    }

    static class PaymentMethodViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;
        TextView label;
        TextView method;

        public PaymentMethodViewHolder(View view) {
            super(view);

            logo = view.findViewById(R.id.logo);
            label = view.findViewById(R.id.label);
            method = view.findViewById(R.id.method);

        }
    }
}