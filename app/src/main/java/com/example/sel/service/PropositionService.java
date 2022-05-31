package com.example.sel.service;

import model.Category;
import model.Proposition;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface PropositionService {

    @GET("/propositions/{id}")
    public Call<Proposition> getById(@Path("id") int id);

    @GET("/propositions")
    public Call<List<Proposition>> getAll() ;
}

