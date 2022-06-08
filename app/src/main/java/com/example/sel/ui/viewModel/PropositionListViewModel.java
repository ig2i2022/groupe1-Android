package com.example.sel.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sel.domain.business.PropostionBusiness;
import com.example.sel.model.Proposition;

import java.util.List;

public class PropositionListViewModel extends ViewModel {

    public void loadProposition(int offset) {
        PropostionBusiness.getInstance().getAll(offset, 15);
    }

    public LiveData<List<Proposition>> getPropositionsList() {
        return PropostionBusiness.getInstance().getPropositionsListLiveData();
    }
}
