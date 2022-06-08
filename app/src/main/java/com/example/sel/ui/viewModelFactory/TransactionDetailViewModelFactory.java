package com.example.sel.ui.viewModelFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.sel.ui.viewModel.TransactionDetailViewModel;

public class TransactionDetailViewModelFactory implements ViewModelProvider.Factory {

    private final int id;

    public TransactionDetailViewModelFactory(int id) {
        this.id = id;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TransactionDetailViewModel(id);
    }
}
