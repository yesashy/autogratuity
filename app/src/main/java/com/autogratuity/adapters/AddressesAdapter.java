// app/src/main/java/com/autogratuity/adapters/AddressesAdapter.java
package com.autogratuity.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autogratuity.R;
import com.autogratuity.models.Address;

import java.text.DecimalFormat;
import java.util.List;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.AddressViewHolder> {
    private List<Address> addresses;
    private Context context;
    private OnAddressClickListener listener;
    private DecimalFormat currencyFormat = new DecimalFormat("$0.00");

    public interface OnAddressClickListener {
        void onAddressClick(Address address);
        void onViewDeliveriesClick(Address address);
    }

    public AddressesAdapter(Context context, List<Address> addresses, OnAddressClickListener listener) {
        this.context = context;
        this.addresses = addresses;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_address, parent, false);
        return new AddressViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressViewHolder holder, int position) {
        Address address = addresses.get(position);

        holder.addressText.setText(address.getFullAddress());

        // Show average tip with color based on amount
        double avgTip = address.getAverageTip();
        holder.averageTipText.setText(currencyFormat.format(avgTip));

        // Set color based on average tip amount
        if (avgTip >= 8.0) {
            holder.averageTipText.setTextColor(context.getResources().getColor(R.color.green_700));
        } else if (avgTip >= 5.0) {
            holder.averageTipText.setTextColor(context.getResources().getColor(R.color.yellow_500));
        } else if (avgTip > 0) {
            holder.averageTipText.setTextColor(context.getResources().getColor(R.color.red_500));
        } else {
            holder.averageTipText.setTextColor(context.getResources().getColor(R.color.gray_400));
        }

        // Show delivery count
        int deliveryCount = address.getDeliveryCount();
        holder.deliveryCountText.setText(deliveryCount + " " +
                (deliveryCount == 1 ? "delivery" : "deliveries"));

        // Handle Do Not Deliver flag
        if (address.isDoNotDeliver()) {
            holder.doNotDeliverText.setVisibility(View.VISIBLE);
        } else {
            holder.doNotDeliverText.setVisibility(View.GONE);
        }

        // Handle click events
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAddressClick(address);
            }
        });

        holder.viewDeliveriesButton.setOnClickListener(v -> {
            if (listener != null) {
                listener.onViewDeliveriesClick(address);
            }
        });
    }

    @Override
    public int getItemCount() {
        return addresses != null ? addresses.size() : 0;
    }

    public void updateAddresses(List<Address> newAddresses) {
        this.addresses = newAddresses;
        notifyDataSetChanged();
    }

    static class AddressViewHolder extends RecyclerView.ViewHolder {
        TextView addressText, averageTipText, deliveryCountText, doNotDeliverText;
        View viewDeliveriesButton;

        AddressViewHolder(@NonNull View itemView) {
            super(itemView);
            addressText = itemView.findViewById(R.id.text_address);
            averageTipText = itemView.findViewById(R.id.text_average_tip);
            deliveryCountText = itemView.findViewById(R.id.text_delivery_count);
            doNotDeliverText = itemView.findViewById(R.id.text_do_not_deliver);
            viewDeliveriesButton = itemView.findViewById(R.id.button_view_deliveries);
        }
    }
}