package com.ralf.module_news.mvp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.ralf.module_news.R;
import com.ralf.module_news.R2;
import com.ralf.module_news.mvp.ui.adapter.NewsFragmentViewPagerAdapter;
import com.ralf.pet_provider.router.RouterConfig;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.ScrollIndicatorView;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import butterknife.BindView;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsFragment
 * @email -
 * @date 2019/05/05 12:21
 **/
@Route(path = RouterConfig.NewsModule.NEWS_PATH)
public class NewsFragment extends BaseFragment {

    @BindView(R2.id.fragment_new_indicator)
    ScrollIndicatorView mIndicatorView;
    @BindView(R2.id.news_view_pager)
    ViewPager mViewPager;

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        float unSelectSize = 13;
        float selectSize = unSelectSize * 17 / 13f;
        mIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(0xFFFFFFFF, Color.WHITE).setSize(selectSize, unSelectSize));
        mIndicatorView.setScrollBar(new ColorBar(this.getContext(), 0xFFFFFFFF, 10));
        mViewPager.setOffscreenPageLimit(3);
        IndicatorViewPager indicatorViewPager = new IndicatorViewPager(mIndicatorView, mViewPager);
        indicatorViewPager.setAdapter(new NewsFragmentViewPagerAdapter(getContext(), getActivity().getSupportFragmentManager()));
    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
