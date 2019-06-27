package com.ralf.module_service.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_service.mvp.contract.SameCityContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetAdoptionPresenter
 * @email -
 * @date 2019/06/19 18:31
 **/
@ActivityScope
public class SameCityPresenter extends BasePresenter<SameCityContract.Model,SameCityContract.View> {

    @Inject
    public SameCityPresenter(SameCityContract.Model model, SameCityContract.View rootView) {
        super(model, rootView);
    }
}
