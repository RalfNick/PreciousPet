package com.ralf.module_service.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_service.R;
import com.ralf.module_service.dg.component.DaggerPetAdoptionComponent;
import com.ralf.module_service.dg.module.PetAdoptionModule;
import com.ralf.module_service.mvp.contract.PetAdoptionContract;
import com.ralf.module_service.mvp.presenter.PetAdoptionPresenter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlineActivity
 * @email -
 * @date 2019/06/19 18:13
 **/
@Route(path = RouterConfig.ServiceModule.PATH_PET_ADOPTION)
public class PetAdoptionActivity extends BaseSwipeBackActivity<PetAdoptionPresenter>
        implements PetAdoptionContract.View {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPetAdoptionComponent.builder()
                .appComponent(appComponent)
                .petAdoptionModule(new PetAdoptionModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_pet_adoption;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }
}
