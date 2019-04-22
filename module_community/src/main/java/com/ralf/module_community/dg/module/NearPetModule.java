package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.mvp.contract.NearPetContract;
import com.ralf.module_community.mvp.model.NeatPetModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseModule
 * @email -
 * @date 2019/04/15 上午11:34
 **/
@Module
public class NearPetModule {

    private NearPetContract.View mView;

    public NearPetModule(NearPetContract.View view) {
        mView = view;
    }

    @Provides
    public NearPetContract.View provideNearPetView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public NearPetContract.Model provideNearPetModel(NeatPetModel model) {
        return model;
    }
}
