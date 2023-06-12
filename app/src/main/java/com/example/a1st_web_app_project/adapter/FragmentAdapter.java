package com.example.a1st_web_app_project.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.a1st_web_app_project.fragment.FragmentFirst;
import com.example.a1st_web_app_project.fragment.FragmentFourth;
import com.example.a1st_web_app_project.fragment.FragmentSecond;
import com.example.a1st_web_app_project.fragment.FragmentThird;

public class FragmentAdapter extends FragmentStateAdapter {

    public int mCount;

    public FragmentAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) return new FragmentFirst();
        else if(index==1) return new FragmentSecond();
        else if(index==2) return new FragmentThird();
        else return new FragmentFourth();

    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) { return position % mCount; }

}