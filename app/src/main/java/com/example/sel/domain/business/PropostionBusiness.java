package com.example.sel.domain.business;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sel.model.Profile;
import com.example.sel.model.Proposition;
import com.example.sel.data.service.ProfileService;
import com.example.sel.data.service.PropositionService;
import com.example.sel.data.service.ServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropostionBusiness {

    private static PropostionBusiness instance;
    private final PropositionService propositionService;
    private final ProfileService profileService;
    private final MutableLiveData<Proposition> propositionLiveData;
    private final MutableLiveData<List<Proposition>> propositionsListLiveData;

    private PropostionBusiness() {
        propositionLiveData = new MutableLiveData<>();
        propositionsListLiveData = new MutableLiveData<>();
        propositionService = ServiceGenerator.createService(PropositionService.class);
        profileService = ServiceGenerator.createService(ProfileService.class);

    }

    public static PropostionBusiness getInstance() {
        if (instance == null) {
            instance = new PropostionBusiness();
        }
        return instance;
    }

    public void getById(int id) {
        propositionService.getById(id).enqueue(new Callback<Proposition>() {
            @Override
            public void onResponse(Call<Proposition> call, Response<Proposition> response) {
                if (response.isSuccessful()) {
                    Proposition proposition = response.body();
                    propositionLiveData.postValue(proposition);
                    populateWithUser(proposition);
                }
            }

            @Override
            public void onFailure(Call<Proposition> call, Throwable t) {
                Log.d("Profile Activity", t.toString());
                //Todo : Manage failure
            }
        });
    }

    public void getAll(int offset, int limit) {
        propositionService.getAll(offset, limit).enqueue(new Callback<List<Proposition>>() {
            @Override
            public void onResponse(Call<List<Proposition>> call, Response<List<Proposition>> response) {
                if (response.isSuccessful()) {
                    propositionsListLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Proposition>> call, Throwable t) {

            }
        });
    }


    private void populateWithUser(Proposition proposition) {
        profileService.getById(proposition.getIdMembre()
        ).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.isSuccessful()) {
                    proposition.setProfile(response.body());
                    propositionLiveData.postValue(proposition);
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }


    public LiveData<Proposition> getPropositionLiveData() {
        return propositionLiveData;
    }

    public LiveData<List<Proposition>> getPropositionsListLiveData() {
        return propositionsListLiveData;
    }
}
