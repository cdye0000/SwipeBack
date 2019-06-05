package com.cdye.swipeback;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public  class MyPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragments=new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public void addFragment(Fragment fragment){
        fragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}

