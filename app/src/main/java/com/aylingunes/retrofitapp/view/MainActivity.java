package com.aylingunes.retrofitapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.aylingunes.retrofitapp.R;
import com.aylingunes.retrofitapp.model.CryptoModel;
import com.aylingunes.retrofitapp.service.CryptoAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {



    //data indirme

    ArrayList<CryptoModel> cryptoModels;
    private  String BASE_URL="https://api.nomics.com/v1/";
    Retrofit retrofit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // https://api.nomics.com/v1/prices?key=527359ecf8a6c7c563515d12cb3fe00c

        Gson gson = new GsonBuilder().setLenient().create();


retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();


loadData();
    }


    private void loadData(){

        CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        Call<List<CryptoModel>> call = cryptoAPI.getData();


        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {

                if(response.isSuccessful()){ // gelen cevap başarılıkysa kontrolu
                    List<CryptoModel> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList); // listeyi arraylisete çevir
for(CryptoModel cryptoModel : cryptoModels){
    System.out.println(cryptoModel.currency);
}


                }


            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace(); // hata halinde
            }
        });


    }
}