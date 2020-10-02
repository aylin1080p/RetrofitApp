package com.aylingunes.retrofitapp.service;

import com.aylingunes.retrofitapp.model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {
    // get, post, update, delete işlemleri olabilir. biz get yapacağız
    //url base --> www.site.com yazılmıyor get içine
    // get---<price?key=zczxczxc şeklinde olmalı
    @GET("prices?key=527359ecf8a6c7c563515d12cb3fe00c")
    //  // https://api.nomics.com/v1/prices?key=527359ecf8a6c7c563515d12cb3fe00c
    Call<List<CryptoModel>> getData();



}
