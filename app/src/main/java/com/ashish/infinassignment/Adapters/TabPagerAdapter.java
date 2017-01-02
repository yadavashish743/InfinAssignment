package com.ashish.infinassignment.Adapters;

/**
 * Created by Ashish Yadav on 27-Dec-16.
 */

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class TabPagerAdapter extends FragmentPagerAdapter {

    private Activity context;
    private ArrayList<Fragment> fragments ;
    private String[] titles ;



    public TabPagerAdapter(Activity context, FragmentManager fm, ArrayList<Fragment> fragments, String[]titles) {
        super(fm);
        this.context = context;
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position){
        return titles[position];
    }}

