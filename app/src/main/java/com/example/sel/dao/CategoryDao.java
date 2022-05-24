package com.example.sel.dao;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sel.exception.SELErrorCode;
import com.example.sel.exception.SELException;
import com.example.sel.service.ServiceGenerator;

import model.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.CategoryService;


import java.io.IOException;
import java.util.List;

public class CategoryDao implements Dao<Category>{
    private static CategoryDao instance;
    private final CategoryService service;

    public final MutableLiveData<List<Category>> listCategories = new MutableLiveData<>();

    private CategoryDao(){
        service = ServiceGenerator.createService(CategoryService.class);
    }

    public static CategoryDao getInstance(){
        if (instance == null){
            instance = new CategoryDao();
        }
        return instance;
    }

    @Override
    public void create(Category object) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public Category get(int id) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override public List<Category> getAll() throws SELException{return null;}

    public void getAll2() {

            Call<List<Category>> callAsync = service.getAll();
            callAsync.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                    Log.d("DAO","TROP FORT");
                    if (response.isSuccessful()){
                        listCategories.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {
                    //Todo : Manage failure
                }
            });


    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public void update(Category object) {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
