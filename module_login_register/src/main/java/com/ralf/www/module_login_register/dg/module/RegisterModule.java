package com.ralf.www.module_login_register.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.www.module_login_register.mvp.contact.RegisterContact;
import com.ralf.www.module_login_register.mvp.model.RegisterModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RegisterModule
 * @email -
 * @date 2018/12/11 上午11:53
 **/
@Module
public class RegisterModule {

    private RegisterContact.View mView;

    public RegisterModule(RegisterContact.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    RegisterContact.View provideRegisterView() {
        return mView;
    }

    @ActivityScope
    @Provides
    RegisterContact.Model provideRegisterModel(RegisterModel model) {
        return model;
    }
}
