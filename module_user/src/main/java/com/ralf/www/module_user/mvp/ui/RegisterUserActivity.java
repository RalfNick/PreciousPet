package com.ralf.www.module_user.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.ralf.www.module_user.R;
import com.ralf.www.pet_provider.router.RouterConfig;
import com.ralf.www.pet_provider.util.StringUtils;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RegisterUserActivity
 * @email -
 * @date 2018/12/11 下午1:21
 **/
@Route(path = RouterConfig.UserModule.REGISTER_USER_PATH)
public class RegisterUserActivity extends BaseActivity {

    private String mPhone;
    private String mPassWord;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_register_user;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        Intent intent = getIntent();
        mPhone = intent.getStringExtra(RouterConfig.UserModule.KEY_USER_PHONE);
        mPassWord = intent.getStringExtra(RouterConfig.UserModule.KEY_USER_PASSWORD);

    }
}
