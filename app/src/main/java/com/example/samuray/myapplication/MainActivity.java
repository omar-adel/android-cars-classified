package com.example.samuray.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;





public class MainActivity extends AppCompatActivity {

    public static Integer brand_id_var = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }



    public void onAddButtonClick(View view)
    {
            if (!IsEmptyEditTextAdd()){
                AddPostServer();
            }
    }


    public void onSelectBrandButtonClick(View view)
    {
        Intent intent = new Intent(this, BrandListActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}
        Integer brand_id = data.getIntExtra("Brand_id_extra",0);
        String brand_title = data.getStringExtra("Brand_name_extra");

        brand_id_var = brand_id;

        Button brand_button = (Button)findViewById(R.id.BrandBtn);
        brand_button.setText(brand_title);

    }



    public void AddPostServer() {

        
        final EditText et_add_title = (EditText)findViewById(R.id.add_ed_title);
        final EditText et_add_text = (EditText)findViewById(R.id.add_ed_text);
        final EditText et_add_price = (EditText)findViewById(R.id.add_ed_price);



        final Integer add_str_brand = brand_id_var;
        final String add_str_title = et_add_title.getText().toString();
        final String add_str_text = et_add_text.getText().toString();


        String price_before_format = et_add_price.getText().toString();
        price_before_format = price_before_format.trim();
        final int add_str_price = !add_str_text.equals("")?Integer.parseInt(price_before_format) : 0;




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DjangoApi.DJANGO_SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DjangoApi postApi= retrofit.create(DjangoApi.class);


        CarModel carModel = new CarModel(
                add_str_brand,
                add_str_title,
                add_str_text,
                add_str_price
        );


        Call<RequestBody> call = postApi.addCar(carModel);

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
