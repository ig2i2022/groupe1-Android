package com.example.sel.data.service;

import com.example.sel.model.Commentaire;
import com.example.sel.model.Proposition;
import com.example.sel.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TransactionService {

    @GET("/transactions/{id}")
    Call<Transaction> getById(@Path("id") int id);

    @GET("/transactions")
    Call<List<Transaction>> getAll(@Query("offset") int offset, @Query("limit") int limit);

    @POST("/transactions/{id}/commentaires")
    Call<Proposition> addCommentaire(@Path("id") int id, @Body Commentaire commentaire);

    @GET("/transactions/{id}/commentaires")
    Call<List<Commentaire>> getAllCommentaire(@Path("id") int id);

}
