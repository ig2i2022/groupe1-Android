package com.example.sel.ui.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sel.domain.business.ProfileBusiness;
import com.example.sel.model.Profile;

public class ProfileViewModel extends ViewModel {

    private final int id;

    public ProfileViewModel(int id) {
        this.id = id;
    }

    public void loadProfile() {
        ProfileBusiness.getInstance().getById(id);
    }

    public LiveData<Profile> getProfile() {
        return ProfileBusiness.getInstance().getProfile();
    }

}
