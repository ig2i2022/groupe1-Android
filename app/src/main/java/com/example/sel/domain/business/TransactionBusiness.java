package com.example.sel.domain.business;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sel.data.service.ProfileService;
import com.example.sel.data.service.ServiceGenerator;
import com.example.sel.data.service.TransactionService;
import com.example.sel.model.Commentaire;
import com.example.sel.model.Profile;
import com.example.sel.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionBusiness {

    private static TransactionBusiness instance;
    private final TransactionService transactionService;
    private final ProfileService profileService;
    private final MutableLiveData<Transaction> transactionLiveData;
    private final MutableLiveData<List<Transaction>> transactionsListLiveData;
    private final MutableLiveData<List<Commentaire>> commentairesListLiveData;

    private TransactionBusiness() {
        transactionLiveData = new MutableLiveData<>();
        transactionsListLiveData = new MutableLiveData<>();
        commentairesListLiveData = new MutableLiveData<>();
        transactionService = ServiceGenerator.createService(TransactionService.class);
        profileService = ServiceGenerator.createService(ProfileService.class);

    }

    public static TransactionBusiness getInstance() {
        if (instance == null) {
            instance = new TransactionBusiness();
        }
        return instance;
    }

    public void getById(int id) {
        transactionService.getById(id).enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                if (response.isSuccessful()) {
                    Transaction transaction = response.body();
                    transactionLiveData.postValue(transaction);
                    populateWithUser(transaction);
                }
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                Log.d("Profile Activity", t.toString());
                //Todo : Manage failure
            }
        });
    }

    public void getAll(int offset, int limit) {
        transactionService.getAll(offset, limit).enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
                if (response.isSuccessful()) {
                    transactionsListLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Transaction>> call, Throwable t) {

            }
        });
    }


    private void populateWithUser(Transaction transaction) {
        profileService.getById(transaction.getIdBeneficaire()).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.isSuccessful()) {
                    transaction.setBeneficiare(response.body());
                    transactionLiveData.postValue(transaction);
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
        profileService.getById(transaction.getIdDonneur()).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.isSuccessful()) {
                    transaction.setDonneur(response.body());
                    transactionLiveData.postValue(transaction);
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }

    public void addCommentaire(int id, Commentaire commentaire) {
        commentaire.setParentId(id);
        transactionService.addCommentaire(id, commentaire).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                getAllCommentaire(id);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                //TODO : manage error
            }
        });

    }

    public void getAllCommentaire(int transactionId) {
        transactionService.getAllCommentaire(transactionId).enqueue(
                new Callback<List<Commentaire>>() {
                    @Override
                    public void onResponse(Call<List<Commentaire>> call, Response<List<Commentaire>> response) {
                        if (response.isSuccessful()) {
                            commentairesListLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Commentaire>> call, Throwable t) {

                    }
                });
    }


    public LiveData<Transaction> getTransactionLiveData() {
        return transactionLiveData;
    }

    public LiveData<List<Transaction>> getTransactionsListLiveData() {
        return transactionsListLiveData;
    }

    public LiveData<List<Commentaire>> getCommentairesListLiveData() {
        return commentairesListLiveData;
    }
}
