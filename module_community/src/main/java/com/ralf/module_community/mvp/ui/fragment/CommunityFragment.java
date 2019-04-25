package com.ralf.module_community.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.SizeUtils;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.mvp.ui.adapter.CommunityAdapter;
import com.ralf.pet_provider.router.RouterConfig;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommunityFragment
 * @email -
 * @date 2018/12/19 上午11:51
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_PATH)
public class CommunityFragment extends BaseFragment {

    private static final int PIC_IMAGE_HIDE_POSITION = 2;

    @BindView(R2.id.take_picture_iv)
    ImageView mPictureIv;

    @BindView(R2.id.community_tab_layout)
    TabLayout mTabLayout;

    @BindView(R2.id.community_view_pager)
    ViewPager mViewPager;

    private List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_community, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mFragmentList.add(new FeaturedFragment());
        mFragmentList.add(new RecommendFragment());
        mFragmentList.add(new ChannelFragment());
        CommunityAdapter adapter = new CommunityAdapter(getChildFragmentManager(), mFragmentList);
        setUpIndicatorWidth(mTabLayout, 10, 10);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(2);

        // 在位置2时不显示发布状态的相机按钮
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == PIC_IMAGE_HIDE_POSITION) {
                    mPictureIv.setVisibility(View.INVISIBLE);
                } else {
                    mPictureIv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    /**
     * 通过反射设置 tabLayout 的下面横线的宽度
     *
     * @param tabLayout
     * @param marginLeft
     * @param marginRight
     */
    private void setUpIndicatorWidth(TabLayout tabLayout, int marginLeft, int marginRight) {
        Class<?> tabLayoutClass = tabLayout.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayoutClass.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        LinearLayout layout = null;
        try {
            if (tabStrip != null) {
                layout = (LinearLayout) tabStrip.get(tabLayout);
            }
            if (layout != null) {
                for (int i = 0; i < layout.getChildCount(); i++) {
                    View child = layout.getChildAt(i);
                    child.setPadding(0, 0, 0, 0);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
                    params.setMarginStart(SizeUtils.dp2px(marginLeft));
                    params.setMarginEnd(SizeUtils.dp2px(marginRight));
                    child.setLayoutParams(params);
                    child.invalidate();
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R2.id.take_picture_iv)
    public void onViewClicked() {
        ToastUtils.showShort("去拍照");
    }
}
