package com.example.sel.ui.viewHolder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sel.R;
import com.example.sel.model.Proposition;
import com.example.sel.ui.activity.PropositionDetailActivity;

public class PropositionViewHolder extends RecyclerView.ViewHolder {


    private final TextView textView;
    private final LinearLayout containter_proprosition_item;
    private final Context context;

    public PropositionViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        textView = itemView.findViewById(R.id.text_comm);
        containter_proprosition_item = itemView.findViewById(R.id.containter_proprosition_item);
    }

    public void updateWithProposition(Proposition proposition) {
        textView.setText(String.format("%d - %s", proposition.getId(), proposition.getDescription()));
        containter_proprosition_item.setOnClickListener((view) -> {
            Intent intent = new Intent(context, PropositionDetailActivity.class);
            intent.putExtra("com.example.sel.PROPOSITION_ID", Integer.toString(proposition.getId()));
            context.startActivity(intent);
        });

    }

}
