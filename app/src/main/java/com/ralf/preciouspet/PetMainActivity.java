package com.ralf.preciouspet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.orhanobut.logger.Logger;
import com.ralf.pet_provider.base.SimpleObserver;
import com.ralf.pet_provider.router.RouterConfig;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;

/**
 * @author wanglixin
 */
@Route(path = RouterConfig.AppModule.MAIN_PATH)
public class PetMainActivity extends BaseActivity {

    @BindView(R.id.main_fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.main_view_switch_rg)
    RadioGroup mViewSwitchRg;

    /**
     * 当前选择的 Fragment 的路径
     */
    private String mFramentPath;
    private Set<String> mFragmentTagSet = new HashSet<>();

    private FragmentManager mFragmentManager;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_pet_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        // 首先显示社区
        mFramentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
        mFragmentManager = getSupportFragmentManager();
        checkNetWork();
        initRaidoCheckedEvent();
    }

    /**
     * 检查网络
     */
    private void checkNetWork() {

    }

    /**
     * 跳转到不同的 Fragment
     */
    private void initRaidoCheckedEvent() {

        RxRadioGroup.checkedChanges(mViewSwitchRg)
                .subscribe(new SimpleObserver<Integer>() {
                    @Override
                    protected void onSuccess(Integer data) {
                        switch (data) {
                            case R.id.main_community_rb:
                                mFramentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
                                break;
                            case R.id.main_information_rb:
//                                mFramentPath = RouterConfig.NewsModule.NEWS_PATH;
                                mFramentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
                                break;
                            case R.id.main_service_rb:
//                                mFramentPath = RouterConfig.ServiceModule.SERVICE_PATH;
                                mFramentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
                                break;
                            case R.id.main_shopping_rb:
//                                mFramentPath = RouterConfig.MallModule.MALL_PATH;
                                mFramentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
                                break;

                            default:
                                mFramentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
                                break;
                        }
                        switchFragment();
                    }

                    @Override
                    protected void onFailed() {
                        Logger.e("跳转到fragment失败");
                    }
                });
    }

    /**
     * 切换到不同的 Fragment
     */
    private void switchFragment() {

        mFragmentTagSet.add(mFramentPath);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideAllFragments(transaction);
        Fragment fragment = mFragmentManager.findFragmentByTag(mFramentPath);
        if (fragment == null) {
            fragment = (Fragment) ARouter.getInstance().build(mFramentPath).navigation();
            transaction.add(R.id.main_fragment_container, fragment, mFramentPath);
        }
        transaction.show(fragment);
        transaction.commit();
    }

    /**
     * 隐藏所有的 Fragment
     * @param transaction
     */
    private void hideAllFragments(FragmentTransaction transaction) {

        if (mFragmentTagSet.isEmpty()) {
            return;
        }
        for (String tag : mFragmentTagSet) {
            Fragment fragment = mFragmentManager.findFragmentByTag(tag);
            if (fragment != null){
                transaction.hide(fragment);
            }
        }
    }
}
