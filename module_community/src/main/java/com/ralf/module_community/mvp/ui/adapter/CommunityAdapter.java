package com.ralf.module_community.mvp.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommunityAdapter
 * @email -
 * @date 2018/12/19 下午2:14
 **/
public class CommunityAdapter extends FragmentPagerAdapter {

    private static final String[] TITLES = {"精选", "推荐", "频道"};
    private List<Fragment> mFragmentList;

    public CommunityAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        mFragmentList = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
