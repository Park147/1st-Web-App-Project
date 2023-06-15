package com.example.a1st_web_app_project.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.a1st_web_app_project.R;

public class FragmentFirst extends Fragment {
    private String data;

    public FragmentFirst(String data) {
        this.data = data;
    }
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_1p, container, false);

        return rootView;
    }
}