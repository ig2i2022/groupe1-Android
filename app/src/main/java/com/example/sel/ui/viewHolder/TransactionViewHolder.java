package com.example.sel.ui.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sel.R;
import com.example.sel.model.Transaction;
import com.example.sel.ui.activity.TransactionDetailActivity;

public class TransactionViewHolder extends RecyclerView.ViewHolder {


    private final TextView textView;
    private final LinearLayout containter_proprosition_item;
    private final Context context;

    public TransactionViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        textView = itemView.findViewById(R.id.text_comm);
        containter_proprosition_item = itemView.findViewById(R.id.containter_proprosition_item);
    }

    public void updateWithTransaction(Transaction transaction) {
        textView.setText(String.format("%d - %s", transaction.getId(), transaction.getDescription()));
        containter_proprosition_item.setOnClickListener((view) -> {
            Intent intent = new Intent(context, TransactionDetailActivity.class);
            intent.putExtra("com.example.sel.TRANSACTION_ID", Integer.toString(transaction.getId()));
            context.startActivity(intent);
        });

    }

}
