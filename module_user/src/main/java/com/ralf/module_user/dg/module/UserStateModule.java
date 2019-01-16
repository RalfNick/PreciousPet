package com.ralf.module_user.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_user.mvp.contact.UserStateContact;
import com.ralf.module_user.mvp.model.UserStateModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name UserStateModule
 * @email -
 * @date 2019/01/16 下午1:48
 **/
@Module
public class UserStateModule {

    private UserStateContact.View mView;

    public UserStateModule(UserStateContact.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public UserStateContact.View provideUserStateView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public UserStateContact.Model provideUserStateModel(UserStateModel model) {
        return model;
    }
}
