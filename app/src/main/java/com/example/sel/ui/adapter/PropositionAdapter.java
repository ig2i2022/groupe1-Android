package com.example.sel.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sel.R;
import com.example.sel.model.Proposition;
import com.example.sel.ui.viewHolder.PropositionViewHolder;

import java.util.List;

public class PropositionAdapter extends RecyclerView.Adapter<PropositionViewHolder> {

    // FOR DATA
    private final List<Proposition> propositions;

    public PropositionAdapter(List<Proposition> propositions) {
        this.propositions = propositions;
    }


    @NonNull
    @Override
    public PropositionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.proposition_item, parent, false);

        return new PropositionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropositionViewHolder viewHolder, int position) {
        viewHolder.updateWithProposition(this.propositions.get(position));
    }

    @Override
    public int getItemCount() {
        return propositions.size();
    }
}
