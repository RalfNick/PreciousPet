package com.ralf.module_user.mvp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.ralf.module_user.R;
import com.ralf.module_user.dg.component.DaggerMasterInfoComponent;
import com.ralf.module_user.dg.module.MasterInfoModule;
import com.ralf.module_user.mvp.contact.MasterInfoContact;
import com.ralf.module_user.mvp.presenter.MasterInfoPresenter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * 用户关注，状态，粉丝等信息
 * @name MasterInfoActivity
 * @email -
 * @date 2019/01/16 下午1:45
 **/
@Route(path = RouterConfig.UserModule.MASTER_INFO_PATH)
public class MasterInfoActivity extends BaseSwipeBackActivity<MasterInfoPresenter> implements MasterInfoContact.View {

    @Autowired(name = RouterConfig.UserModule.KEY_USER_ID)
    int userId;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerMasterInfoComponent.builder()
                .appComponent(appComponent)
                .masterInfoModule(new MasterInfoModule(this))
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
