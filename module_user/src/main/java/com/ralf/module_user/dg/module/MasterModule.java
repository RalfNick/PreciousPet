package com.ralf.module_user.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_user.mvp.contact.MasterContract;
import com.ralf.module_user.mvp.model.MasterModel;

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
public class MasterModule {

    private MasterContract.View mView;

    public MasterModule(MasterContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public MasterContract.View provideUserStateView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public MasterContract.Model provideUserStateModel(MasterModel model) {
        return model;
    }
}
