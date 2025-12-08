package com.wsj.tabbarapplication.service;

import com.wsj.tabbarapplication.pojo.User;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author: 绫_N
 * @date: 2025/12/4
 * @description: myAppDemo_wsj
 */
public interface ApiService {
    @GET("/api/user")
    Call<User> getUser();

    @GET("/api/upload")
    retrofit2.Call<String> upload(
            @retrofit2.http.Query("title") String title,
            @retrofit2.http.Query("price") String price,
            @retrofit2.http.Query("description") String description,
            @retrofit2.http.Query("image") String imageBase64
    );
    @GET("/api/list")
    Call<String> getList();
}
