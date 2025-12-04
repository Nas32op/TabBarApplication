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
}
