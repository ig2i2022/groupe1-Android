package com.example.sel.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sel.domain.business.TransactionBusiness;
import com.example.sel.model.Commentaire;
import com.example.sel.model.Transaction;

import java.util.List;

public class TransactionDetailViewModel extends ViewModel {
    private final int id;


    public TransactionDetailViewModel(int id) {
        this.id = id;
    }

    public void loadTransaction() {
        TransactionBusiness.getInstance().getById(id);
    }

    public LiveData<Transaction> getProposition() {
        return TransactionBusiness.getInstance().getTransactionLiveData();
    }

    public void addCommentaire(Commentaire commentaire) {
        TransactionBusiness.getInstance().addCommentaire(id, commentaire);
    }

    public void loadAllCommentaire() {
        TransactionBusiness.getInstance().getAllCommentaire(id);
    }

    public LiveData<List<Commentaire>> getAllCommentaire() {
        return TransactionBusiness.getInstance().getCommentairesListLiveData();
    }
}
