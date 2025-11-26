package com.wsj.tabbarapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.wsj.tabbarapplication.R;
import com.wsj.tabbarapplication.controller.CommodityAdapterR;
import com.wsj.tabbarapplication.pojo.Commodity;
import java.util.ArrayList;
import java.util.List;
/**
 * @author: 绫_N
 * @date: 2025/11/19
 * @description: TabBarApplication
 */
public class Fragment2 extends Fragment{
    private Commodity commodity;
    private List<Commodity> commodities = new ArrayList<>();
    public Fragment2() {
        super(R.layout.fragment_2);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initData();
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        CommodityAdapterR adapter = new CommodityAdapterR(commodities);
        //传入数据
        adapter.setOnItemClickListener(new CommodityAdapterR.OnItemClickListener() {
            @Override
            public void onItemClick(Commodity commodity) {
//                commodity_mod commodity_mod = new commodity_mod();
//                Bundle bundle = new Bundle();
//                bundle.putString("kName", commodity.getName());
//                bundle.putString("kPrice", commodity.getPrice());
//                bundle.putString("kDesc", commodity.getDesc());
//                commodity_mod.setArguments(bundle);
//                getParentFragmentManager()
//                        .beginTransaction()
//                        .replace(android.R.id.content, commodity_mod)
//                        .addToBackStack(null)//添加返回
//                        .commit();
                //跳转到新页面并传递当前参数
                Intent intent = new Intent(getActivity(), commodity_mod.class);
                intent.putExtra("kName", commodity.getName());
                intent.putExtra("kPrice", commodity.getPrice());
                intent.putExtra("kDesc", commodity.getDesc());
                //传递照片使用id
                intent.putExtra("kImgId", commodity.getImgId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        return view;
    }
    private void initData() {
        String[] names ={
                "99新 iPhone 14 Pro Max",
                "宜家双人布艺沙发",
                "复古胶片相机 Canon AE-1",
                "AirPods Pro 二代",
                "Switch 塞尔达传说限定版",
                "机械键盘 Keychron",
                "露营全套装备（帐篷/天幕/桌椅）",
                "星巴克限量猫爪杯",
                "露营全套装备（帐篷/天幕/桌椅）",
                "星巴克限量猫爪杯",
                "露营全套装备（帐篷/天幕/桌椅）",
                "星巴克限量猫爪杯",
                "99新 iPhone 14 Pro Max",
                "宜家双人布艺沙发",
                "复古胶片相机 Canon AE-1",
                "AirPods Pro 二代",
                "Switch 塞尔达传说限定版",
                "机械键盘 Keychron",
                "露营全套装备（帐篷/天幕/桌椅）",
                "星巴克限量猫爪杯",
                "露营全套装备（帐篷/天幕/桌椅）",
                "星巴克限量猫爪杯",
                "露营全套装备（帐篷/天幕/桌椅）",
                "星巴克限量猫爪杯",
                "全新未拆封 PS5 光驱版"
        };
        String[] descs ={
                "Apple 苹果 iPhone 14 Pro Max 5G 全网通 6G+128G 官方旗舰店Apple 苹果 iPhone 14 Pro Max 5G 全网通 6G+128G 官方旗舰店",
                "宜家 2021年新款 2人沙发 软木艺 实木家具 实木沙发",
                "Canon 佳能 AE-1 胶片相机 35mm 35mm 35mm",
                "Apple 苹果 AirPods Pro 2代 带无线充电盒",
                "Sony 索尼 塞尔达传说限定版 nes 64",
                "机械键盘 键盘 键盘 键盘 键盘 键盘 键盘 键盘 键盘 键盘",
                "露营用品 帐篷/天幕/桌椅",
                "露营用品 帐篷/天幕/桌椅",
                "露营用品 帐篷/天幕/桌椅",
                "露营用品 帐篷/天幕/桌椅",
                "露营用品 帐篷/天幕/桌椅",
                "露营用品 帐篷/天幕/桌椅",
                "Apple 苹果 iPhone 14 Pro Max 5G 全网通 6G+128G 官方旗舰店Apple 苹果 iPhone 14 Pro Max 5G 全网通 6G+128G 官方旗舰店",
                "宜家 2021年新款 2人沙发 软木艺 实木家具 实木沙发",
                "Canon 佳能 AE-1 胶片相机 35mm 35mm 35mm",
                "Apple 苹果 AirPods Pro 2代 带无线充电盒",
                "Sony 索尼 塞尔达传说限定版 nes 64",
                "机械键盘 键盘 键盘 键盘 键盘 键盘 键盘 键盘 键盘 键盘",
                "露营用品 帐篷/天幕/桌椅",
                "露营用品 帐篷/天幕/桌椅",
                "露营用品 帐篷/天幕/桌椅",
                "露营用品 帐篷/天幕/桌椅",
                "露营用品 帐篷/天幕/桌椅",
                "露营用品 帐篷/天幕/桌椅",
                "露营用品 帐篷/天幕/桌椅"
        };
        String[] prices ={
                "￥9,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥9,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00",
                "￥1,999.00"
        };
        int[] images = {
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku,
                R.drawable.miku
        };
        for (int i = 0; i < names.length; i++){
            commodity = new Commodity(names[i], prices[i], descs[i], images[i]);
            commodities.add(commodity);
        }
    }
}
