package com.ralf.module_service.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_service.mvp.contract.PetPairContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetAdoptionPresenter
 * @email -
 * @date 2019/06/19 18:31
 **/
@ActivityScope
public class PetPairPresenter extends BasePresenter<PetPairContract.Model,PetPairContract.View> {

    @Inject
    public PetPairPresenter(PetPairContract.Model model, PetPairContract.View rootView) {
        super(model, rootView);
    }
}
