package com.example.taskapp.viewmodel;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.taskapp.model.DataModel;
import com.example.taskapp.network.APIService;
import com.example.taskapp.network.RetroInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DataListViewModel extends ViewModel {

    private MutableLiveData<List<DataModel>> dataList;


    public DataListViewModel(){
        dataList = new MutableLiveData<>();
    }

    public MutableLiveData<List<DataModel>> getDataListObserver() {
        return dataList;

    }

    public void makeApiCall() {


        APIService apiService = RetroInstance.getRetroClient().create(APIService.class);
        Call<List<DataModel>> call = apiService.getDataList();
        call.enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {

                Log.d("000147","RESPONSE: " +response.body().toString());

                dataList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {
                Log.d("000147","Error: " + t.getMessage());
                dataList.postValue(null);
            }
        });
    }
}
