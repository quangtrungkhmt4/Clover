package com.ben.clover.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {
    private int numberTabs;
    private List<? extends Fragment> fragments;

    public PageAdapter(FragmentManager fm, int numberTabs, List<? extends Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.numberTabs = numberTabs;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return numberTabs;
    }
}