package com.example.samuray.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class BrandListActivity extends AppCompatActivity {

    private ArrayList<Integer> idPost = new ArrayList<>();
    private ArrayList<Integer> brandId = new ArrayList<>();
    private ArrayList<String> nameBrand = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand_list);

        showAllPosts();

    }


    private void showAllPosts() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DjangoApi.DJANGO_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DjangoApi postApi= retrofit.create(DjangoApi.class);



        Call<List<BrandModel>> call = postApi.getBrand();

        call.enqueue(new Callback<List<BrandModel>>() {
            @Override
            public void onResponse(Call<List<BrandModel>> call, Response<List<BrandModel>> response) {

                if(response.isSuccessful()){

                    if (response.body() != null) {

                        List<BrandModel> heroList = response.body();

                        for(BrandModel h:heroList){

                            Integer id_post = h.getId();
                            idPost.add(id_post);

                            Integer id_brand = h.getIdbrand();
                            brandId.add(id_brand);


                            String title = h.getName();
                            nameBrand.add(title);

                        }

                        initRecyclerView();

                    }

                }else {
                    Log.d("fail", "fail");
                }


            }

            @Override
            public void onFailure(Call<List<BrandModel>> call, Throwable t) {
                Log.d("fail", "fail");
            }

        });

    }


    private void initRecyclerView(){

        RecyclerView recyclerView = findViewById(R.id.recyclerv_view);
        RecyclerAdapter adapter = new RecyclerAdapter(this, idPost, nameBrand, brandId);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }





}
