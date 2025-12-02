package com.wsj.tabbarapplication.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.wsj.tabbarapplication.R;
import com.wsj.tabbarapplication.adapter.BannerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * @author: 绫_N
 * @date: 2025/11/19
 * @description: TabBarApplication
 */
public class Fragment1 extends Fragment {

    /**
     * 构造函数：初始化Fragment并指定布局文件
     */
    public Fragment1() {
        super(R.layout.fragment_1);
    }

    private ViewPager2 bannerViewPager;
    Runnable autoScrollRunnable;
    private Handler handler = new Handler(Looper.getMainLooper());

    /**
     * 创建Fragment视图时调用，用于加载布局、设置ViewPager适配器及自动轮播逻辑
     *
     * @param inflater           布局填充器
     * @param container          父容器视图组
     * @param savedInstanceState 保存的实例状态（若存在）
     * @return 返回创建好的视图对象
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 加载fragment_1.xml布局文件
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        // 初始化Banner ViewPager组件
        bannerViewPager = view.findViewById(R.id.bannerViewPager);

        // 设置Banner图片资源列表
        List<Integer> bannerImages = Arrays.asList(
                R.drawable.p_banner,
                R.drawable.airpod_banner,
                R.drawable.canon_banner,
                R.drawable.jianpan_banner,
                R.drawable.switch_banner
        );

        // 创建并设置Banner适配器
        BannerAdapter adapter = new BannerAdapter(bannerImages);
        bannerViewPager.setAdapter(adapter);

        // 定义自动滚动任务：每5秒切换到下一张图片
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int next = (bannerViewPager.getCurrentItem() + 1) % bannerImages.size();
                bannerViewPager.setCurrentItem(next, true);
                handler.postDelayed(this, 5000);
            }
        };

        // 启动自动滚动任务
        handler.postDelayed(runnable, 5000);

        // 获取左右点击区域控件，并绑定点击事件以手动切换图片
        View leftArea = view.findViewById(R.id.leftClickArea);
        View rightArea = view.findViewById(R.id.rightClickArea);

        leftArea.setOnClickListener(v -> {
            int prev = bannerViewPager.getCurrentItem() - 1;
            if (prev < 0) prev = bannerImages.size() - 1;
            bannerViewPager.setCurrentItem(prev, true);

            resetAutoScroll();
        });

        rightArea.setOnClickListener(v -> {
            int next = (bannerViewPager.getCurrentItem() + 1) % bannerImages.size();
            bannerViewPager.setCurrentItem(next, true);

            resetAutoScroll();
        });

        return view;
    }

    /**
     * 重置自动滚动定时器，在用户手动操作后重新开始计时
     */
    private void resetAutoScroll() {
        handler.removeCallbacks(autoScrollRunnable);
        handler.postDelayed(autoScrollRunnable, 5000);
    }
}
