package com.wsj.tabbarapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.wsj.tabbarapplication.R;
import com.wsj.tabbarapplication.pojo.User;
import com.wsj.tabbarapplication.service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: 绫_N
 * @date: 2025/11/19
 * @description: TabBarApplication
 */
public class Fragment4 extends Fragment{
    private TextView tvResult;
    private Button btnRequest;
    public Fragment4() {
        super(R.layout.fragment_4);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_4, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 注意：这里必须用 view.findViewById
        tvResult = view.findViewById(R.id.tv_result);
        btnRequest = view.findViewById(R.id.btn_request);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchData();
            }
        });
//        super.onViewCreated(view, savedInstanceState);
    }
    private void fetchData() {
        // 禁用按钮，防止重复点击
        btnRequest.setEnabled(false);
        tvResult.setText("请求中...");

        // 初始化 Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.8") //后端地址
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getUser().enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // Fragment 可能在请求结束前被销毁，加个判断防止崩溃
                if (!isAdded()) return;
                btnRequest.setEnabled(true);
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    tvResult.setText("后端返回：\n" + "UserName:"+user.getName()+", Age:"+user.getAge());
                } else {
                    tvResult.setText("错误码：" + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if (!isAdded()) return;

                btnRequest.setEnabled(true);
                tvResult.setText("连接失败");
                // Fragment 中使用 Toast 需要 getContext()
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
