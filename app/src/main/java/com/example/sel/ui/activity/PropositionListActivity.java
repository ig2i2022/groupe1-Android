package com.example.sel.ui.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sel.R;
import com.example.sel.model.Proposition;
import com.example.sel.ui.adapter.PropositionAdapter;
import com.example.sel.ui.util.EndlessRecyclerViewScrollListener;
import com.example.sel.ui.viewModel.PropositionListViewModel;

import java.util.ArrayList;
import java.util.List;

public class PropositionListActivity extends ParentActivity {

    private RecyclerView recyclerView;
    private List<Proposition> propositions;
    private PropositionAdapter adapter;
    private PropositionListViewModel propositionListViewModel;
    private EndlessRecyclerViewScrollListener scrollListener;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition_list);
        //new NoInternetLayout.Builder(this, R.layout.activity_proposition_list); //change activity_main with your layout
        recyclerView = findViewById(R.id.proposition_recycler_view);
        setFooterBar();
        swipeContainer = findViewById(R.id.proposition_swipe_container);
        configureRecyclerView();
        propositionListViewModel = new ViewModelProvider(this).get(PropositionListViewModel.class);
        propositionListViewModel.getPropositionsList().observe(this, this::updateUI);
        propositionListViewModel.loadProposition(0);


    }


    private void configureRecyclerView() {
        propositions = new ArrayList<>();
        adapter = new PropositionAdapter(propositions);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);

        scrollListener = new EndlessRecyclerViewScrollListener(layout) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                propositionListViewModel.loadProposition(totalItemsCount);

            }
        };
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clear();
                propositionListViewModel.loadProposition(0);
            }
        });
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);
        //Add color because is fun
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void updateUI(List<Proposition> propositions) {
        this.propositions.addAll(propositions);
        adapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }

    // Clean all elements of the recycler
    private void clear() {
        propositions.clear();
        adapter.notifyDataSetChanged();
    }
}