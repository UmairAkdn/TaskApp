package com.example.taskapp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.adapter.DataListAdapter;
import com.example.taskapp.model.DataModel;
import com.example.taskapp.viewmodel.DataListViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataListAdapter.ItemClickListener {

    private List<DataModel> dataModelList;
    public static DataListAdapter adapter;
    private DataListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final TextView noresult = findViewById(R.id.tvNoResult);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter =  new DataListAdapter(this, dataModelList, this);
        recyclerView.setAdapter(adapter);

        //Set Divider
        /*DividerItemDecoration horizontalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(this, R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        recyclerView.addItemDecoration(horizontalDecoration);*/



        viewModel = ViewModelProviders.of(this).get(DataListViewModel.class);
        viewModel.getDataListObserver().observe(this, new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> dataModels) {
                if(dataModels != null) {
                    dataModelList = dataModels;
                    adapter.setDataList(dataModels);
                    noresult.setVisibility(View.GONE);
                } else {
                    noresult.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }

    @Override
    public void onDataClick(DataModel data) {

        Toast.makeText(this, "Click on ID: "+ data.getId(), Toast.LENGTH_SHORT).show();

    }


}