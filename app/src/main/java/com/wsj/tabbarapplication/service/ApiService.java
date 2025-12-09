package com.wsj.tabbarapplication.service;

import com.wsj.tabbarapplication.pojo.Release;
import com.wsj.tabbarapplication.pojo.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
    Call<List<Release>> getRelease();
}