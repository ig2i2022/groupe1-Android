package com.example.sel.domain.business;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.sel.model.Profile;
import com.example.sel.data.service.ProfileService;
import com.example.sel.data.service.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileBusiness {

    private static ProfileBusiness instance;
    private final ProfileService service;
    private final MutableLiveData<Profile> profile;

    private ProfileBusiness() {
        profile = new MutableLiveData<>();
        service = ServiceGenerator.createService(ProfileService.class);
    }

    public static ProfileBusiness getInstance() {
        if (instance == null) {
            instance = new ProfileBusiness();
        }
        return instance;
    }

    public void getById(int id) {
        service.getById(id).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if (response.isSuccessful()) {
                    profile.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.d("Profile Activity", t.toString());
                //Todo : Manage failure
            }
        });


    }

    public LiveData<Profile> getProfile() {
        return profile;
    }
}
