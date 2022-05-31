package com.example.sel.dao;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.sel.exception.SELErrorCode;
import com.example.sel.exception.SELException;
import com.example.sel.service.PropositionService;
import com.example.sel.service.ServiceGenerator;

import model.Proposition;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;
import java.util.List;

public class PropostionDao implements Dao<Proposition> {
    private static PropostionDao instance;
    private final PropositionService service;
    public final MutableLiveData<List<Proposition>> listProposition= new MutableLiveData<>();

    private PropostionDao(){
        service = ServiceGenerator.createService(PropositionService.class);
    }

    public static PropostionDao getInstance(){
        if (instance == null){
            instance = new PropostionDao();
        }
        return instance;
    }

    @Override
    public void create(Proposition object) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public Proposition get(int id) throws SELException {
        try {
            Call<Proposition> callSync = service.getById(id);
            Response<Proposition> response = callSync.execute();
            if (!response.isSuccessful()){
                throw new SELException(SELErrorCode.API2);
            }
            return response.body();
        } catch (IOException e) {
            throw new SELException(SELErrorCode.API1,e);
        }
    }

    @Override
    public List<Proposition> getAll() throws SELException {
        try {
            Call<List<Proposition>> callSync = service.getAll();
            Response<List<Proposition>> response = callSync.execute();
            if (!response.isSuccessful()){
                throw new SELException(SELErrorCode.API2);
            }
            return response.body();
        } catch (IOException e) {
            throw new SELException(SELErrorCode.API1,e);
        }
    }

    public void getAll2() {

        Call<List<Proposition>> callAsync = service.getAll();
        callAsync.enqueue(new Callback<List<Proposition>>() {
            @Override
            public void onResponse(Call<List<Proposition>> call, Response<List<Proposition>> response) {
                Log.d("DAO","TROP FORT");
                if (response.isSuccessful()){
                    listProposition.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Proposition>> call, Throwable t) {
                //Todo : Manage failure
            }
        });


    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public void update(Proposition object) {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
