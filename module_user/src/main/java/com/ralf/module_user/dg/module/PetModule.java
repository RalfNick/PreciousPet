package com.ralf.module_user.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_user.mvp.contact.PetContract;
import com.ralf.module_user.mvp.model.PetModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MasterModule
 * @email -
 * @date 2019/01/16 下午1:48
 **/
@Module
public class PetModule {

    private PetContract.View mView;

    public PetModule(PetContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public PetContract.View providePetContractView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public PetContract.Model providePetContractModel(PetModel model) {
        return model;
    }
}
