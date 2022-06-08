package com.example.sel.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.sel.R;
import com.example.sel.model.Proposition;
import com.example.sel.ui.viewModel.PropositionDetailViewModel;
import com.example.sel.ui.viewModelFactory.PropositionDetailViewModelFactory;

public class PropositionDetailActivity extends ParentActivity {

    public static final String USER_ID = "com.example.sel.USER_ID";

    private PropositionDetailViewModel propositionDetailViewModel;
    private TextView description;
    private TextView price;
    private TextView pseudo;
    private CardView propositionProfileContainer;
    private ImageView profilCircle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition_detail);
        setFooterBar();
        description = findViewById(R.id.descriptionProposition);
        price = findViewById(R.id.price);
        pseudo = findViewById(R.id.pseudoInProp);
        propositionProfileContainer = findViewById(R.id.propositionProfileContainer);
        Intent intent = getIntent();
        int proposition_id = Integer.parseInt(intent.getStringExtra("com.example.sel.PROPOSITION_ID"));
        propositionDetailViewModel = new ViewModelProvider(this, new PropositionDetailViewModelFactory(proposition_id)).get(PropositionDetailViewModel.class);
        propositionDetailViewModel.getProposition().observe(this, this::setPropositionInformation);
        propositionDetailViewModel.loadProposition();
        profilCircle = findViewById(R.id.CircleImage2);

    }

    private void setPropositionInformation(Proposition proposition) {
        Log.d("PROP", proposition.toString());
        description.setText(String.format("%d - %S", proposition.getId(), proposition.getDescription()));
        price.setText(String.format("%d Ecu", proposition.getPrix()));
        if (proposition.getProfile() != null) {
            pseudo.setText(proposition.getProfile().getPseudo());
            propositionProfileContainer.setOnClickListener((view) -> goToProfile(proposition.getProfile().getId()));
            if (proposition.getProfile().getId() % 2 == 0) {
                Glide.with(this).load(String.format("https://randomuser.me/api/portraits/men/%s.jpg", proposition.getProfile().getId() % 100)).into(profilCircle);
            } else {
                Glide.with(this).load(String.format("https://randomuser.me/api/portraits/women/%s.jpg", proposition.getProfile().getId() % 100)).into(profilCircle);

            }

        }


    }

    private void goToProfile(int id) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(USER_ID, Integer.toString(id));
        startActivity(intent);
    }


}