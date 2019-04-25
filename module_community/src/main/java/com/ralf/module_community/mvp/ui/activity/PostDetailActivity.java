package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.dg.component.DaggerPostComponent;
import com.ralf.module_community.dg.module.PostModule;
import com.ralf.module_community.mvp.contract.PostContract;
import com.ralf.module_community.mvp.presenter.PostPresenter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * 帖子详情
 * @name ChannelActivity
 * @email -
 * @date 2019/04/24 上午11:09
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_POST_DETAIL_PATH)
public class PostDetailActivity extends BaseSwipeBackActivity<PostPresenter> implements PostContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPostComponent.builder()
                .appComponent(appComponent)
                .postModule(new PostModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_post_channel_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }
}
