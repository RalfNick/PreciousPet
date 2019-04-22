package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.mvp.contract.PetListContract;
import com.ralf.module_community.mvp.model.PetListModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PraiseListModule
 * @email -
 * @date 2019/04/08 上午8:01
 **/
@Module
public class PetListModule {

    private PetListContract.View mView;

    public PetListModule(PetListContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public PetListContract.View providePetListContractView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public PetListContract.Model providePetListContractModel(PetListModel model) {
        return model;
    }
}
