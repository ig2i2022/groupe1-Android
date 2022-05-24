package com.example.sel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sel.dao.CategoryDao;
import com.example.sel.dao.PropostionDao;
import com.example.sel.exception.SELException;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView l;
    String[] tutorials
            = {"Algorithms", "Data Structures",
            "Languages", "Interview Corner",
            "GATE", "ISRO CS",
            "UGC NET CS", "CS Subjects",
            "Web Technologies"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = findViewById(R.id.list);
        PropostionDao.getInstance().getAll2();
        PropostionDao.getInstance().listProposition.observe(this, data -> {
            Log.d("Main Activity", data.toString());
            String[] names = data.stream().map(p -> p.getDescription()).toArray(size -> new String[data.size()]);
            String[] categories = data.stream().map(p -> String.format("%s : %s", p.getCategorie(), p.getCompetence())).toArray(size -> new String[data.size()]);
            MyListAdapter arr = new MyListAdapter(
                    this,
                    names,
                    categories);
            l.setAdapter(arr);
        });
        Log.d("Test", "Hello World");


    }
}