package com.ralf.www.module_user.dg.module;

import android.support.v4.app.FragmentActivity;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.www.module_user.mvp.contact.RegisterContact;
import com.ralf.www.module_user.mvp.contact.RegisterUserContact;
import com.ralf.www.module_user.mvp.model.RegisterModel;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RegisterUserModule
 * @email -
 * @date 2018/12/12 上午9:46
 **/
@Module
public abstract class RegisterUserModule {

    @ActivityScope
    @Provides
    static RxPermissions provideRxPermissions(RegisterUserContact.View view) {
        return new RxPermissions((FragmentActivity) view.getActivity());
    }

    @Binds
    abstract RegisterContact.Model provideRegisterUserModel(RegisterModel model);
}
