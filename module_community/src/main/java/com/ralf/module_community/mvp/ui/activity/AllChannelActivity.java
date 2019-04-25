package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.dg.component.DaggerAllChannelComponent;
import com.ralf.module_community.dg.module.AllChannelModule;
import com.ralf.module_community.mvp.contract.AllChannelContract;
import com.ralf.module_community.mvp.presenter.AllChannelPresenter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelActivity
 * @email -
 * @date 2019/04/24 上午11:09
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_ALL_CHANNEL_PATH)
public class AllChannelActivity extends BaseSwipeBackActivity<AllChannelPresenter> implements AllChannelContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerAllChannelComponent.builder()
                .appComponent(appComponent)
                .allChannelModule(new AllChannelModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_all_channel;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }
}
