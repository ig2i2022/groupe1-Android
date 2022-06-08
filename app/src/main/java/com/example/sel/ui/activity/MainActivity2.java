package com.example.sel.ui.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sel.R;

public class MainActivity2 extends AppCompatActivity {
    public static final String USER_ID = "com.example.sel.USER_ID";
    Button profilButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        profilButton = findViewById(R.id.profilButton);
    }


}