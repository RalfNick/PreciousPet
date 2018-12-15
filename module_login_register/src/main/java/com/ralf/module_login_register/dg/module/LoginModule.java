package com.ralf.module_login_register.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_login_register.mvp.contact.LoginContact;
import com.ralf.module_login_register.mvp.model.LoginModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LoginModule
 * @email -
 * @date 2018/12/13 上午10:45
 **/

@Module
public class LoginModule {

    private LoginContact.View mView;

    public LoginModule(LoginContact.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public LoginContact.View provideLoginView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public LoginContact.Model provideLoginModel(LoginModel model) {
        return model;
    }
}
