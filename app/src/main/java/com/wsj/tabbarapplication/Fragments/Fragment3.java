package com.wsj.tabbarapplication.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.wsj.tabbarapplication.R;
import com.wsj.tabbarapplication.service.ApiService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author: 绫_N
 * @date: 2025/11/19
 * @description: TabBarApplication
 */
public class Fragment3 extends Fragment {

    private ImageView imgAddPicture;
    private EditText etTitle, etPrice, etDesc;
    private Button btnPublish;
    private LinearLayout selectImgLayout;

    private Uri selectedImageUri;
    private ActivityResultLauncher<String> imagePickerLauncher;

    public Fragment3() {
        super(R.layout.fragment_3);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        initView(view);
        initLauncher();
        initListener();
        return view;
    }

    private void initView(View view) {
        selectImgLayout = view.findViewById(R.id.selectImg);
        imgAddPicture = view.findViewById(R.id.img_add_picture);
        etTitle = view.findViewById(R.id.et_title);
        etPrice = view.findViewById(R.id.et_price);
        etDesc = view.findViewById(R.id.et_desc);
        btnPublish = view.findViewById(R.id.btn_publish);
    }

    private void initLauncher() {
        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                selectedImageUri = uri;
                Glide.with(this).load(uri).into(imgAddPicture);
            }
        });
    }

    private void initListener() {
        selectImgLayout.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));

        btnPublish.setOnClickListener(v -> uploadData());
    }

    private void uploadData() {
        String title = etTitle.getText().toString().trim();
        String price = etPrice.getText().toString().trim();
        String desc = etDesc.getText().toString().trim();

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(getContext(), "请输入商品名称", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(price)) {
            Toast.makeText(getContext(), "请输入价格", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(desc)) {
            Toast.makeText(getContext(), "请输入描述", Toast.LENGTH_SHORT).show();
            return;
        }
        if (selectedImageUri == null) {
            Toast.makeText(getContext(), "请选择图片", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            try {
                String base64Image = uriToBase64(selectedImageUri);
                if (base64Image == null) {
                    showToast("图片处理失败");
                    return;
                }
                sendRequest(title, price, desc, base64Image);
            } catch (Exception e) {
                e.printStackTrace();
                showToast("发生错误: " + e.getMessage());
            }
        }).start();
    }

    private String uriToBase64(Uri uri) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            
            // Compress bitmap to avoid URL length issues or OOM
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            // Compress to JPEG, quality 50 to reduce size
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
            byte[] imageBytes = outputStream.toByteArray();
            
            // Close streams
            if (inputStream != null) inputStream.close();
            outputStream.close();

            return Base64.encodeToString(imageBytes, Base64.NO_WRAP);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void sendRequest(String title, String price, String desc, String base64Image) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.111.5/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        Call<String> call = apiService.upload(title, price, desc, base64Image);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    showToast(response.body());
                } else {
                    showToast("上传失败: " + response.code());
                    Log.e("Fragment3", "Upload failed: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                showToast("网络请求失败: " + t.getMessage());
                Log.e("Fragment3", "Network error", t);
            }
        });
    }

    private void showToast(String message) {
        new Handler(Looper.getMainLooper()).post(() -> 
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show()
        );
    }
}
