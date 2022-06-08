package com.example.sel.data.service;


import com.example.sel.model.Proposition;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PropositionService {

    @GET("/propositions/{id}")
    Call<Proposition> getById(@Path("id") int id);

    @GET("/propositions")
    Call<List<Proposition>> getAll(@Query("offset") int offset, @Query("limit") int limit);

}

