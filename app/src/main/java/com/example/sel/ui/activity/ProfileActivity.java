package com.example.sel.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.sel.R;
import com.example.sel.model.Profile;
import com.example.sel.ui.viewModel.ProfileViewModel;
import com.example.sel.ui.viewModelFactory.ProfileViewModelFactory;

public class ProfileActivity extends ParentActivity {

    private TextView pseudo;
    private TextView hoursNumber;
    private TextView name;
    private TextView email;
    private ProfileViewModel profileViewModel;
    private ImageView proIcon;
    private ImageView profilTop;
    private ImageView profilCircle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        pseudo = findViewById(R.id.pseudo);
        hoursNumber = findViewById(R.id.hours_number);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        proIcon = findViewById(R.id.proIcon);
        profilTop = findViewById(R.id.image);
        profilCircle = findViewById(R.id.CircleImage);
        Intent intent = getIntent();
        int profile_id = Integer.parseInt(intent.getStringExtra(MainActivity2.USER_ID));
        profileViewModel = new ViewModelProvider(this, new ProfileViewModelFactory(profile_id)).get(ProfileViewModel.class);
        profileViewModel.getProfile().observe(this, this::setProfileInformation);
        profileViewModel.loadProfile();
    }


    private void setProfileInformation(Profile profile) {
        if (profile != null) {
            Log.d("Profile Activity", profile.toString());
            pseudo.setText(profile.getPseudo());
            hoursNumber.setText(Integer.toString(profile.getBalance()));
            name.setText(String.format("%s %s", profile.getName(), profile.getFirstName()));
            email.setText(profile.getMail());
            if (profile.isPro()) {
                proIcon.setVisibility(View.VISIBLE);
            } else {
                proIcon.setVisibility(View.INVISIBLE);
            }
            // Just to get random picture photo men or women
            if (profile.getId() % 2 == 0) {
                Glide.with(this).load(String.format("https://randomuser.me/api/portraits/men/%s.jpg", profile.getId() % 100)).into(profilCircle);
                Glide.with(this).load(String.format("https://randomuser.me/api/portraits/men/%s.jpg", profile.getId() % 100)).into(profilTop);
            } else {
                Glide.with(this).load(String.format("https://randomuser.me/api/portraits/women/%s.jpg", profile.getId() % 100)).into(profilCircle);
                Glide.with(this).load(String.format("https://randomuser.me/api/portraits/women/%s.jpg", profile.getId() % 100)).into(profilTop);

            }


        }

    }
}