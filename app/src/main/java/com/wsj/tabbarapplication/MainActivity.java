package com.wsj.tabbarapplication;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wsj.tabbarapplication.Fragments.Fragment1;
import com.wsj.tabbarapplication.Fragments.Fragment2;
import com.wsj.tabbarapplication.Fragments.Fragment3;
import com.wsj.tabbarapplication.Fragments.Fragment4;
import com.wsj.tabbarapplication.Fragments.Fragment5;
import com.wsj.tabbarapplication.adapter.MyFragmentAdapter;
import com.wsj.tabbarapplication.pojo.Commodity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private MyFragmentAdapter myFragmentAdapter;
    private TabLayout tabLayout;
    private String[] titles = {"首页", "闲置", "发布", "消息","我的"};
    private int icon[] = {
            R.drawable.home,
            R.drawable.xianzhi,
            R.drawable.fabu,
            R.drawable.xiaoxi,
            R.drawable.me
    };
    private List<Fragment> fragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 开启沉浸式，内容扩展到屏幕底部（系统小白条悬浮）
//        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
//        setContentView(R.layout.activity_main);
//        View bottomBar = findViewById(R.id.main);
//        ViewCompat.setOnApplyWindowInsetsListener(bottomBar, (v, insets) -> {
//            int bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom;
////            v.setPadding(0, 0, 0, bottom); // 防止底部导航被小白条挡住
//            return WindowInsetsCompat.CONSUMED;
//        });
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        initTab();
    }
    //初始化列表和导航栏
    private void initTab() {
        fragments = new ArrayList<Fragment>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
        myFragmentAdapter = new MyFragmentAdapter(this, fragments);
        viewPager.setAdapter(myFragmentAdapter);
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int i) {
//                        tab.setText(titles[i]);
                        tab.setIcon(icon[i]);
                    }
                }).attach();
    }
}