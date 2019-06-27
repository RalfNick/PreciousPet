package com.ralf.module_service.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_service.mvp.contract.PetAdoptionContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetAdoptionPresenter
 * @email -
 * @date 2019/06/19 18:31
 **/
@ActivityScope
public class PetAdoptionPresenter extends BasePresenter<PetAdoptionContract.Model,PetAdoptionContract.View> {

    @Inject
    public PetAdoptionPresenter(PetAdoptionContract.Model model, PetAdoptionContract.View rootView) {
        super(model, rootView);
    }
}
