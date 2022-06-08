package com.example.sel.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sel.R;

public abstract class ParentActivity extends AppCompatActivity {
    public static final String USER_ID = "com.example.sel.USER_ID";


    public void setFooterBar() {
        Button profilButton = findViewById(R.id.profilButton);
        Button transactionButton = findViewById(R.id.transactionButton);
        Button homeButton = findViewById(R.id.homeButton);
        profilButton.setOnClickListener(this::goToUserProfile);
        transactionButton.setOnClickListener(this::goToTransactionList);
        homeButton.setOnClickListener(this::goToPropositionList);
    }

    public void goToPropositionList(View view) {
        Intent intent = new Intent(this, PropositionListActivity.class);
        startActivity(intent);
    }

    public void goToTransactionList(View view) {
        Intent intent = new Intent(this, TransactionListActivity.class);
        startActivity(intent);
    }

    public void goToUserProfile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra(USER_ID, Integer.toString(5));
        startActivity(intent);
    }


}
