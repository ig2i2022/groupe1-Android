package com.example.sel.data.service;

import com.example.sel.model.Commentaire;
import com.example.sel.model.Proposition;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentaireService {

    @POST("/transactions/{id}/commentaires")
    Call<Proposition> add(@Path("id") int id, @Body Commentaire commentaire);

}
