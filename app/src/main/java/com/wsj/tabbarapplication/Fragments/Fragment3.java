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
//    private String apiUrl = "http://192.168.111.5/";
    private String apiUrl = "http://172.20.10.8/";
    private ImageView imgAddPicture;
    private EditText etTitle, etPrice, etDesc;
    private Button btnPublish;
    private LinearLayout selectImgLayout;
    private Uri selectedImageUri;
    private ActivityResultLauncher<String> imagePickerLauncher;
    /**
     * 构造方法：初始化 Fragment 并指定布局文件为 fragment_3。
     */
    public Fragment3() {
        super(R.layout.fragment_3);
    }
    /**
     * 创建视图时调用的方法。
     *
     * @param inflater           布局加载器
     * @param container          父容器视图组
     * @param savedInstanceState 上一次保存的状态 Bundle（如果有的话）
     * @return 返回创建好的视图对象
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        initView(view);
        initLauncher();
        initListener();
        return view;
    }
    /**
     * 初始化界面控件，绑定 XML 中定义的组件。
     *
     * @param view 当前 Fragment 的根视图
     */
    private void initView(View view) {
        selectImgLayout = view.findViewById(R.id.selectImg);
        imgAddPicture = view.findViewById(R.id.img_add_picture);
        etTitle = view.findViewById(R.id.et_title);
        etPrice = view.findViewById(R.id.et_price);
        etDesc = view.findViewById(R.id.et_desc);
        btnPublish = view.findViewById(R.id.btn_publish);
    }
    /**
     * 初始化图像选择器启动器，注册回调来处理从相册选取图片的结果。
     */
    private void initLauncher() {
        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
            if (uri != null) {
                selectedImageUri = uri;
                Glide.with(this).load(uri).into(imgAddPicture);
            }
        });
    }
    /**
     * 设置点击监听事件：
     * - 点击图片区域打开相册选择图片；
     * - 点击发布按钮执行上传操作。
     */
    private void initListener() {
        selectImgLayout.setOnClickListener(v -> imagePickerLauncher.launch("image/*"));
        btnPublish.setOnClickListener(v -> uploadData());
    }
    /**
     * 执行上传逻辑：
     * 检查表单字段是否填写完整并选择了图片，
     * 若验证通过则在子线程中进行图片转 Base64 和发送请求的操作。
     */
    private void uploadData() {
        String title = etTitle.getText().toString().trim();
        String price = etPrice.getText().toString().trim();
        String desc = etDesc.getText().toString().trim();
        //表单校验
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
        // 启动新线程执行耗时任务
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
    /**
     * 将 URI 转换为 Base64 字符串格式。
     *
     * @param uri 图片资源的 Uri 地址
     * @return 成功转换后返回 Base64 字符串；否则返回 null
     */
    private String uriToBase64(Uri uri) {
        try {
            InputStream inputStream = requireContext().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream); // 使用JPEG格式压缩至50%
            byte[] imageBytes = outputStream.toByteArray();
            if (inputStream != null) inputStream.close();
            outputStream.close();
            return Base64.encodeToString(imageBytes, Base64.NO_WRAP);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 发送 HTTP 请求到服务端上传商品信息。
     *
     * @param title       商品标题
     * @param price       商品价格
     * @param desc        商品描述
     * @param base64Image 经过 Base64 编码后的图片字符串
     */
    private void sendRequest(String title, String price, String desc, String base64Image) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(ScalarsConverterFactory.create()) // 支持字符串响应体解析
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
    /**
     * 在主线程显示 Toast 提示消息。
     * @param message 需要提示的消息文本
     */
    private void showToast(String message) {
        new Handler(Looper.getMainLooper()).post(() ->
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show()
        );
    }
}