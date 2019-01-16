package com.ralf.module_user.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_user.mvp.contact.MasterInfoContact;
import com.ralf.module_user.mvp.model.MasterInfoModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MasterInfoModule
 * @email -
 * @date 2019/01/16 下午1:48
 **/
@Module
public class MasterInfoModule {

    private MasterInfoContact.View mView;

    public MasterInfoModule(MasterInfoContact.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public MasterInfoContact.View provideUserStateView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public MasterInfoContact.Model provideUserStateModel(MasterInfoModel model) {
        return model;
    }
}
