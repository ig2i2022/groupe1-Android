package com.example.sel.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sel.domain.business.TransactionBusiness;
import com.example.sel.model.Transaction;

import java.util.List;

public class TransactionListViewModel extends ViewModel {

    public void loadProposition(int offset) {
        TransactionBusiness.getInstance().getAll(offset, 15);
    }

    public LiveData<List<Transaction>> getTransactionsList() {
        return TransactionBusiness.getInstance().getTransactionsListLiveData();
    }
}
