/*
 * CS-499
 * 4-2 Milestone Three: Enhancement Two: Algorithms and Data Structure
 * Bourama Mangara
 * 26 July 2020
 */


package com.example.lcs2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryAdapter.OrderHistoryViewHolder> {
    Context mContext;
    ArrayList<Order> order2ArrayList;




    public OrderHistoryAdapter(Context context, ArrayList<Order> arrayList){
        mContext = context;
        this.order2ArrayList = arrayList;

    }

    public OrderHistoryAdapter(@NonNull Context context) {
    }

    public class OrderHistoryViewHolder extends RecyclerView.ViewHolder{
        public TextView total, orderID, orderDate, orderStatus;
        public OrderHistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            total = itemView.findViewById(R.id.total);
            orderID = itemView.findViewById(R.id.orderID);
            orderDate = itemView.findViewById(R.id.orderDate);
            orderStatus = itemView.findViewById(R.id.orderStatus);

        }
    }
    @NonNull
    @Override
    public OrderHistoryAdapter.OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item, parent, false);
        OrderHistoryAdapter.OrderHistoryViewHolder historyViewHolder = new OrderHistoryAdapter.OrderHistoryViewHolder(view);
        return historyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        final Order order2 = order2ArrayList.get(position);
        holder.total.setText("$"+order2.getTotal().toString());
        holder.orderID.setText(String.valueOf(order2.getOrderID())); // make sure to convert to string
        holder.orderDate.setText(order2.getDate());
        holder.orderStatus.setText(order2.getStatus());

    }



    @Override
    public int getItemCount() {
        return order2ArrayList.size();
    }
}
