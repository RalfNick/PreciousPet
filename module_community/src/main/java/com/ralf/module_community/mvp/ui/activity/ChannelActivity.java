package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.dg.component.DaggerChannelDetailComponent;
import com.ralf.module_community.dg.module.ChannelDetailModule;
import com.ralf.module_community.mvp.contract.ChannelDetailContract;
import com.ralf.module_community.mvp.presenter.ChannelDetailPresenter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelActivity
 * @email -
 * @date 2019/04/24 上午11:09
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_CHANNEL_DETAIL_PATH)
public class ChannelActivity extends BaseSwipeBackActivity<ChannelDetailPresenter> implements ChannelDetailContract.View {

    @Autowired(name = RouterConfig.CommunityModule.KEY_CHANNEL_DETAIL_ID)
    int mChannelId;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerChannelDetailComponent.builder()
                .appComponent(appComponent)
                .channelDetailModule(new ChannelDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_channel_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }
}
