package com.example.sel.ui.viewModelFactory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.sel.ui.viewModel.PropositionDetailViewModel;

public class PropositionDetailViewModelFactory implements ViewModelProvider.Factory {

    private final int id;

    public PropositionDetailViewModelFactory(int id) {
        this.id = id;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PropositionDetailViewModel(id);
    }
}
