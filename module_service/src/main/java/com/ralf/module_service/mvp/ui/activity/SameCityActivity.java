package com.ralf.module_service.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_service.R;
import com.ralf.module_service.dg.component.DaggerSameCityComponent;
import com.ralf.module_service.dg.module.SameCityModule;
import com.ralf.module_service.mvp.contract.SameCityContract;
import com.ralf.module_service.mvp.presenter.SameCityPresenter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlineActivity
 * @email -
 * @date 2019/06/19 18:13
 **/
@Route(path = RouterConfig.ServiceModule.PATH_SAME_CITY)
public class SameCityActivity extends BaseSwipeBackActivity<SameCityPresenter>
        implements SameCityContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerSameCityComponent.builder()
                .appComponent(appComponent)
                .sameCityModule(new SameCityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_same_city;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }
}
