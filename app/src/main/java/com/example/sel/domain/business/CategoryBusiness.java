package com.example.sel.domain.business;

import androidx.lifecycle.MutableLiveData;

import com.example.sel.data.service.CategoryService;
import com.example.sel.data.service.ServiceGenerator;

import java.util.List;

import model.Category;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryBusiness {

    private static CategoryBusiness instance;
    private final CategoryService service;
    private final MutableLiveData<List<model.Category>> listCategories;

    private CategoryBusiness() {
        listCategories = new MutableLiveData<>();
        service = ServiceGenerator.createService(CategoryService.class);
    }

    public static CategoryBusiness getInstance() {
        if (instance == null) {
            instance = new CategoryBusiness();
        }
        return instance;
    }

    public void getAll() {

        Call<List<model.Category>> callAsync = service.getAll();
        callAsync.enqueue(new Callback<List<model.Category>>() {
            @Override
            public void onResponse(Call<List<model.Category>> call, Response<List<model.Category>> response) {
                if (response.isSuccessful()) {
                    listCategories.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<model.Category>> call, Throwable t) {
                //Todo : Manage failure
            }
        });


    }

    public MutableLiveData<List<Category>> getListCategories() {
        return listCategories;
    }
}
