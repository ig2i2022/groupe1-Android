package com.example.sel.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sel.domain.business.PropostionBusiness;
import com.example.sel.model.Proposition;

public class PropositionDetailViewModel extends ViewModel {
    private final int id;

    public PropositionDetailViewModel(int id) {
        this.id = id;
    }

    public void loadProposition() {
        PropostionBusiness.getInstance().getById(id);
    }

    public LiveData<Proposition> getProposition() {
        return PropostionBusiness.getInstance().getPropositionLiveData();
    }
}
