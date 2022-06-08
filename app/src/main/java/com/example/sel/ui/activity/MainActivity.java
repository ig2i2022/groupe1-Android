package com.example.sel.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.sel.MyListAdapter;
import com.example.sel.R;
import com.example.sel.ui.viewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    ListView l;
    MainViewModel mainViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        mainViewModel.getCategories().observe(this, data -> {
            Log.d("Main Activity", data.toString());
            String[] names = data.stream().map(p -> p.getLabel()).toArray(size -> new String[data.size()]);
            String[] categories = data.stream().map(p -> String.format("%s : %s", p.getLabel(), p.getLabel())).toArray(size -> new String[data.size()]);
            MyListAdapter arr = new MyListAdapter(
                    this,
                    names,
                    categories);
            l.setAdapter(arr);
        });
        mainViewModel.loadCategories();

        Log.d("Test", "Hello World");

    }


}