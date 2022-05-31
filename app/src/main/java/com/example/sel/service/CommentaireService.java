package com.example.sel.service;

import com.example.sel.model.Commentaire;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentaireService {

    @POST("/{id}/commentaires")
    public Call<model.Proposition> add(@Path("id") int id,@Body Commentaire commentaire);

}
