package com.example.sel.ui.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sel.domain.business.CategoryBusiness;
import com.example.sel.dao.CategoryDao;

import java.util.List;

import model.Category;

public class MainViewModel extends ViewModel {


    public MutableLiveData<List<Category>> getCategories() {
        return CategoryDao.getInstance().listCategories;
    }

    public void loadCategories() {
        CategoryBusiness.getInstance().getAll();
    }
}
