package com.example.sel.dao;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sel.exception.SELErrorCode;
import com.example.sel.exception.SELException;
import com.example.sel.model.Commentaire;
import com.example.sel.service.CommentaireService;
import com.example.sel.service.PropositionService;
import com.example.sel.service.ServiceGenerator;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CommentaireDao implements Dao<Commentaire> {

    private static CommentaireDao instance;
    private final CommentaireService service;

    private CommentaireDao(){
        service = ServiceGenerator.createService(CommentaireService.class);
    }

    public static CommentaireDao getInstance(){
        if (instance == null){
            instance = new CommentaireDao();
        }
        return instance;
    }


    @Override
    public void create(Commentaire commentaire) {

        Call callAsync = service.add(commentaire.getParentId(),commentaire);
        callAsync.enqueue(new Callback() {

            @Override
            public void onResponse(Call call, Response response) {
                Log.d("DAO2", response.message());
                Log.d("DAO2","TROP FORT");
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.d("DAO2","TROP NUULL");
            }
        });

    }

    @Override
    public Commentaire get(int id) throws SELException {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public List<Commentaire> getAll() throws SELException, SELException {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public void update(Commentaire object) {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
