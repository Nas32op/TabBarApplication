package com.wsj.tabbarapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wsj.tabbarapplication.adapter.ReleaseAdapter;
import com.wsj.tabbarapplication.pojo.Release;
import com.wsj.tabbarapplication.service.ApiService;
import com.wsj.tabbarapplication.tools.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRelease extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_my_release);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        setContentView(R.layout.activity_my_release);

        // 初始化 RecyclerView
        recyclerView = findViewById(R.id.release_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchDataFromApi();
    }
    private void fetchDataFromApi(){
        ApiService apiService = RetrofitClient.getInstance().create(ApiService.class);
        apiService.getRelease().enqueue(new Callback<List<Release>>() {
            @Override
            public void onResponse(Call<List<Release>> call, Response<List<Release>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Release> productList = response.body();
                    ReleaseAdapter adapter = new ReleaseAdapter(productList,MyRelease.this);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(MyRelease.this, "API 请求失败: Code " + response.code(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Release>> call, Throwable t) {
                t.printStackTrace(); // Print to Logcat
                Toast.makeText(MyRelease.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("errorApi", t.getMessage(), t);
            }
        });
    }
}