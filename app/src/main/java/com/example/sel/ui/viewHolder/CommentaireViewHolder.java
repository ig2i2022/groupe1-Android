package com.example.sel.ui.viewHolder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sel.R;
import com.example.sel.model.Commentaire;

public class CommentaireViewHolder extends RecyclerView.ViewHolder {


    private final TextView commentaire;
    private final TextView pseudo;

    private final ImageView imageView;
    private final Context context;

    public CommentaireViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        commentaire = itemView.findViewById(R.id.text_comm);
        pseudo = itemView.findViewById(R.id.pseudo_comm);
        imageView = itemView.findViewById(R.id.image_comm);
    }

    public void updateWithCommentaire(Commentaire commentaire) {
        this.pseudo.setText(commentaire.getPseudo() + " :");
        if (commentaire.getCommentaire() == "") {
            this.commentaire.setVisibility(View.GONE);
        } else {
            this.commentaire.setText(commentaire.getCommentaire());
        }
        if (commentaire.getId_photo() == 0) {
            imageView.setVisibility(View.GONE);
        } else {
            Glide.with(context).load(String.format("http://192.168.1.60:8081/photos/%d", commentaire.getId_photo())).into(imageView);
        }


    }

}
