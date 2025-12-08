package com.wsj.tabbarapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wsj.tabbarapplication.MyRelease;
import com.wsj.tabbarapplication.R;

/**
 * @author: 绫_N
 * @date: 2025/11/20
 * @description: TabBarApplication
 */
public class Fragment5 extends Fragment {
    public Fragment5() {
        super(R.layout.fragment_5);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_5, container, false);
        view.findViewById(R.id.menu_favorites).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyRelease.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
