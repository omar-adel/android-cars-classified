package com.example.samuray.myapplication;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DjangoApi {

    String DJANGO_SITE = "http://192.168.8.101:8000/api/v1/";
//    http://127.0.0.1:8000/api/v1/add/


    @POST("add/")
    Call<RequestBody> addCar(@Body CarModel carModel);

    @GET("brand/list/")
    Call<List<BrandModel>> getBrand();



}
