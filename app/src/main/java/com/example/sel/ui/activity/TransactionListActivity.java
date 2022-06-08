package com.example.sel.ui.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sel.R;
import com.example.sel.model.Transaction;
import com.example.sel.ui.adapter.TransactionAdapter;
import com.example.sel.ui.util.EndlessRecyclerViewScrollListener;
import com.example.sel.ui.viewModel.TransactionListViewModel;

import java.util.ArrayList;
import java.util.List;

public class TransactionListActivity extends ParentActivity {

    private RecyclerView recyclerView;
    private List<Transaction> transactions;
    private TransactionAdapter adapter;
    private TransactionListViewModel transactionListViewModel;
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
        if (recyclerView != null) {
            setFooterBar();
            swipeContainer = findViewById(R.id.proposition_swipe_container);
            configureRecyclerView();
            transactionListViewModel = new ViewModelProvider(this).get(TransactionListViewModel.class);
            transactionListViewModel.getTransactionsList().observe(this, this::updateUI);
            transactionListViewModel.loadProposition(0);
        }


    }


    private void configureRecyclerView() {
        transactions = new ArrayList<>();
        adapter = new TransactionAdapter(transactions);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layout);

        scrollListener = new EndlessRecyclerViewScrollListener(layout) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                transactionListViewModel.loadProposition(totalItemsCount);

            }
        };
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clear();
                transactionListViewModel.loadProposition(0);
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

    private void updateUI(List<Transaction> transactions) {
        this.transactions.addAll(transactions);
        adapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);

    }

    // Clean all elements of the recycler
    private void clear() {
        transactions.clear();
        adapter.notifyDataSetChanged();
    }
}