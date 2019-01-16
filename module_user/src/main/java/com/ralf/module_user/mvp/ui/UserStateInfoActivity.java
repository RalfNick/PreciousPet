package com.ralf.module_user.mvp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.ralf.module_user.R;
import com.ralf.module_user.dg.component.DaggerUserStateComponent;
import com.ralf.module_user.dg.module.UserStateModule;
import com.ralf.module_user.mvp.contact.UserStateContact;
import com.ralf.module_user.mvp.presenter.UserStatePresenter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * 用户关注，状态，粉丝等信息
 * @name UserStateInfoActivity
 * @email -
 * @date 2019/01/16 下午1:45
 **/
@Route(path = RouterConfig.UserModule.USER_STATE_PATH)
public class UserStateInfoActivity extends BaseSwipeBackActivity<UserStatePresenter> implements UserStateContact.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerUserStateComponent.builder()
                .appComponent(appComponent)
                .userStateModule(new UserStateModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_user_state_info;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
