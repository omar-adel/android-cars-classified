package com.example.samuray.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddPostServer();
    }



    public void onAddButtonClick(View view)
    {
            if (!IsEmptyEditTextAdd()){
                AddPostServer();
            }
    }



    public void AddPostServer() {

        
        EditText et_add_title = (EditText)findViewById(R.id.add_ed_title);
        EditText et_add_text = (EditText)findViewById(R.id.add_ed_text);
        EditText et_add_price = (EditText)findViewById(R.id.add_ed_price);


        String add_str_title = et_add_title.getText().toString();
        String add_str_text = et_add_text.getText().toString();
        int add_str_price = Integer.parseInt(et_add_price.getText().toString());
//        int add_v_cena_uslugi =  Integer.parseInt(v_cena_uslugi.getText().toString());




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DjangoApi.DJANGO_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DjangoApi postApi= retrofit.create(DjangoApi.class);


        CarModel postModel = new CarModel(
                1,
                add_str_title,
                add_str_text,
                1
        );


        Call<RequestBody> call = postApi.addPostVoditel(postModel);

        call.enqueue(new Callback<RequestBody>() {
            @Override
            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                Log.d("good", "good");
            }
            @Override
            public void onFailure(Call<RequestBody> call, Throwable t) {
                Log.d("fail", "fail");
            }
        });

    }



    private Boolean IsEmptyEditTextAdd(){


        EditText et_add_title = (EditText)findViewById(R.id.add_ed_title);
        EditText et_add_text = (EditText)findViewById(R.id.add_ed_text);
        EditText et_add_price = (EditText)findViewById(R.id.add_ed_price);



        if(et_add_title.getText().toString().isEmpty() || et_add_text.getText().toString().isEmpty() || et_add_price.getText().toString().isEmpty() ){
            Toast toast = Toast.makeText(getApplicationContext(),"Edit Text is empty", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();

            return true;

        }else{
            return false;
        }

    }

}
