package com.ralf.module_service.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_service.mvp.contract.PetPairContract;
import com.ralf.module_service.mvp.model.PetPairModel;

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
public class PetPairModule {

    private PetPairContract.View mView;

    public PetPairModule(PetPairContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public PetPairContract.View providePetPairView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public PetPairContract.Model providePetPairModel(PetPairModel model) {
        return model;
    }
}
