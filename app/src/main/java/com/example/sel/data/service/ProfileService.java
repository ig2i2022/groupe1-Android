package com.example.sel.data.service;

import com.example.sel.model.Profile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProfileService {

    @GET("/membres/{id}")
    Call<Profile> getById(@Path("id") int id);

}
