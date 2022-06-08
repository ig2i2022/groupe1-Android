package com.example.sel.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sel.R;
import com.example.sel.model.Commentaire;
import com.example.sel.ui.viewHolder.CommentaireViewHolder;

import java.util.List;

public class CommentaireAdapter extends RecyclerView.Adapter<CommentaireViewHolder> {

    // FOR DATA
    private final List<Commentaire> commentaires;

    public CommentaireAdapter(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }


    @NonNull
    @Override
    public CommentaireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // CREATE VIEW HOLDER AND INFLATING ITS XML LAYOUT
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.commentaire_item, parent, false);

        return new CommentaireViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentaireViewHolder viewHolder, int position) {
        viewHolder.updateWithCommentaire(this.commentaires.get(position));
    }

    @Override
    public int getItemCount() {
        return commentaires.size();
    }
}
