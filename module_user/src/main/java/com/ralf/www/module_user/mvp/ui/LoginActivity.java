package com.ralf.www.module_user.mvp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.ralf.www.module_user.R;
import com.ralf.www.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.www.pet_provider.router.RouterConfig;

/**
 * @author wanglixin
 */
@Route(path = RouterConfig.UserModule.LOGIN_PATH)
public class LoginActivity extends BaseSwipeBackActivity {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
