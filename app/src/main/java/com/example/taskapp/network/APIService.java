package com.example.taskapp.network;

import com.example.taskapp.model.DataModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("posts")
    Call<List<DataModel>> getDataList();
}
