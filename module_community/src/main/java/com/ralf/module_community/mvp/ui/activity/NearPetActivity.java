package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.dg.component.DaggerNearPetComponent;
import com.ralf.module_community.dg.module.NearPetModule;
import com.ralf.module_community.mvp.contract.NearPetContract;
import com.ralf.module_community.mvp.presenter.NearPetPresenter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HeatPraiseActivity
 * @email -
 * @date 2019/04/15 下午12:07
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_HEAT_PRAISE_PATH)
public class NearPetActivity extends BaseSwipeBackActivity<NearPetPresenter> implements NearPetContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerNearPetComponent.builder()
                .appComponent(appComponent)
                .nearPetModule(new NearPetModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_near_pet;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }
}
