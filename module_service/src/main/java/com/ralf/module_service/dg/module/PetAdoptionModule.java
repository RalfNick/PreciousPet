package com.ralf.module_service.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_service.mvp.contract.PetAdoptionContract;
import com.ralf.module_service.mvp.model.PetAdoptionModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlineModule
 * @email -
 * @date 2019/06/19 18:17
 **/
@Module
public class PetAdoptionModule {

    private PetAdoptionContract.View mView;

    public PetAdoptionModule(PetAdoptionContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public PetAdoptionContract.View providePetAdoptionView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public PetAdoptionContract.Model providePetAdoptionModel(PetAdoptionModel model) {
        return model;
    }
}
