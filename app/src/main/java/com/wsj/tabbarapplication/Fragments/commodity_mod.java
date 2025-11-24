package com.wsj.tabbarapplication.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.wsj.tabbarapplication.R;
/**
 * @author: 绫_N
 * @date: 2025/11/20
 * @description: TabBarApplication
 */
public class commodity_mod extends Fragment {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.commodity_mod, container, false);
        //初始化控件
        TextView name = view.findViewById(R.id.tv_name);
        TextView price = view.findViewById(R.id.tv_price);
        TextView desc = view.findViewById(R.id.tv_desc);
        Bundle bundle = getArguments();
        if (bundle != null){
            String kName = bundle.getString("kName");
            String kPrice = bundle.getString("kPrice");
            String kDesc = bundle.getString("kDesc");
            name.setText("您选择的商品是\n商品：\n"+kName);
            price.setText("价格：\n"+kPrice);
            desc.setText("描述："+kDesc);
        }
        return view;
    }
}
