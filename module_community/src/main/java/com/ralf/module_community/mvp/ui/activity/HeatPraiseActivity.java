package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.R2;
import com.ralf.module_community.mvp.ui.adapter.HeatPraisePagerAdapter;
import com.ralf.module_community.mvp.ui.fragment.DayHeatPraiseFragment;
import com.ralf.module_community.mvp.ui.fragment.HistoryHeatPraiseFragment;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HeatPraiseActivity
 * @email -
 * @date 2019/04/15 下午12:07
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_HEAT_PRAISE_PATH)
public class HeatPraiseActivity extends BaseSwipeBackActivity {

    @BindView(R2.id.heat_praise_back_iv)
    ImageView mBackIv;
    @BindView(R2.id.heat_praise_toolbar_first_rb)
    RadioButton mFirstRb;
    @BindView(R2.id.heat_praise_toolbar_second_rb)
    RadioButton mSecondRb;
    @BindView(R2.id.heat_praise_toolbar_rg)
    RadioGroup mToolbarRg;
    @BindView(R2.id.heat_praise_toolbar_btn)
    Button mNoticeBtn;
    @BindView(R2.id.heat_praise_view_pager)
    ViewPager mViewPager;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_heat_praise;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mFirstRb.setText("日榜");
        mSecondRb.setText("历史榜");
        mFirstRb.setChecked(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new DayHeatPraiseFragment());
        fragmentList.add(new HistoryHeatPraiseFragment());
        HeatPraisePagerAdapter pagerAdapter = new HeatPraisePagerAdapter(fragmentManager, fragmentList);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mFirstRb.setChecked(i == 0);
                mSecondRb.setChecked(i == 1);
                mViewPager.setCurrentItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mViewPager.setCurrentItem(0);
    }

    @OnClick({R2.id.heat_praise_back_iv, R2.id.heat_praise_toolbar_first_rb,
            R2.id.heat_praise_toolbar_second_rb, R2.id.heat_praise_toolbar_btn})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.heat_praise_back_iv) {
            finish();
        } else if (i == R.id.heat_praise_toolbar_first_rb) {
            mViewPager.setCurrentItem(0);
        } else if (i == R.id.heat_praise_toolbar_second_rb) {
            mViewPager.setCurrentItem(1);
        } else if (i == R.id.heat_praise_toolbar_btn) {
            ToastUtils.showShort("说明");
        }
    }
}
