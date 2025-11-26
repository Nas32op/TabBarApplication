package com.wsj.tabbarapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.wsj.tabbarapplication.R;
/**
 * @author: 绫_N
 * @date: 2025/11/20
 * @description: TabBarApplication
 */
public class commodity_mod extends AppCompatActivity {
//    private View view;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.commodity_mod, container, false);
//        //初始化控件
//        TextView name = view.findViewById(R.id.tv_name);
//        TextView price = view.findViewById(R.id.tv_price);
//        TextView desc = view.findViewById(R.id.tv_desc);
//        Bundle bundle = getArguments();
//        if (bundle != null){
//            String kName = bundle.getString("kName");
//            String kPrice = bundle.getString("kPrice");
//            String kDesc = bundle.getString("kDesc");
//            name.setText("您选择的商品是\n商品：\n"+kName);
//            price.setText("价格：\n"+kPrice);
//            desc.setText("描述："+kDesc);
//        }
//        return view;
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commodity_mod);

        // 初始化视图控件
        ImageView ivImg = findViewById(R.id.iv_img);
        TextView tvName = findViewById(R.id.tv_name); // 确保ID与布局一致
        TextView tvPrice = findViewById(R.id.tv_price);
        TextView tvDesc = findViewById(R.id.tv_desc);

        // 获取传递的 Intent 数据
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("kName");
            String price = intent.getStringExtra("kPrice");
            String desc = intent.getStringExtra("kDesc");
            int imgId = intent.getIntExtra("kImgId", 0);

            // 设置数据到视图
            tvName.setText(name);
            tvPrice.setText(price);
            tvDesc.setText(desc);
            if (imgId != 0) {
                ivImg.setImageResource(imgId);
            }
        }
}
}
