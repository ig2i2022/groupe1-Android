package service;

import model.Category;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface CategoryService {

    @GET("/categories")
    public Call<List<Category>> getAll() ;
}
