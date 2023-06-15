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

    public int count;
    public String[] dataValues;

    public FragmentAdapter(FragmentActivity fa, int count, String[] dataValues) {
        super(fa);
        Count = count;
        this.dataValues = dataValues;
    }

    public void setFragmentData(int position, String data) {
        Fragment fragment = createFragment(position);
        if (fragment instanceof FragmentFirst) {
            ((FragmentFirst) fragment).setData(data);
        } else if (fragment instanceof FragmentSecond) {
            ((FragmentSecond) fragment).setData(data);
        } else if (fragment instanceof FragmentThird) {
            ((FragmentThird) fragment).setData(data);
        } else if (fragment instanceof FragmentFourth) {
            ((FragmentFourth) fragment).setData(data);
        }
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);
        String data = dataValues[index];

        if(index==0) return new FragmentFirst(data);
        else if(index==1) return new FragmentSecond(data);
        else if(index==2) return new FragmentThird(data);
        else return new FragmentFourth(data);

    }
    @Override
    public int getItemCount() {
        return Count;
    }

    private int getRealPosition(int position) {
        return position % Count;
    }

}


