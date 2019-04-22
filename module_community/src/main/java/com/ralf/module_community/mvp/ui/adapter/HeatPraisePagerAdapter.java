package com.ralf.module_community.mvp.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HeatPraisePagerAdapter
 * @email -
 * @date 2019/04/18 下午9:17
 **/
public class HeatPraisePagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragmentList;

    public HeatPraisePagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.mFragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
