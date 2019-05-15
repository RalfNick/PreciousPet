package com.ralf.module_news.mvp.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jess.arms.utils.SizeUtils;
import com.ralf.module_news.R;
import com.ralf.module_news.constant.NewConstant;
import com.ralf.module_news.mvp.ui.fragment.NewsEmptyFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsFragmentViewPagerAdapter
 * @email -
 * @date 2019/05/15 17:36
 **/
public class NewsFragmentViewPagerAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private Context mContext;

    public NewsFragmentViewPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        mContext = context;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.news_tab_layout, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(NewConstant.VERSION_ARR[position]);
        textView.setPadding(SizeUtils.dp2px(10), 0, SizeUtils.dp2px(10), 0);
        return convertView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        String fragmentClassName = NewConstant.FRAGMENT_NAME_ARR[position];
        try {
            Class<?> clazz = Class.forName(NewConstant.FRAGMENT_PACKAGE_NAME + "." + fragmentClassName);
            Object o = clazz.newInstance();
            if (o instanceof Fragment) {
                return (Fragment) o;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return new NewsEmptyFragment();
    }

    @Override
    public int getItemPosition(Object object) {
        // 这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
        // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public int getCount() {
        return NewConstant.VERSION_ARR.length;
    }
}
