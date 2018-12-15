package com.ralf.module_login_register.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_login_register.dg.module.LoginModule;
import com.ralf.module_login_register.mvp.ui.LoginActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LoginComponent
 * @email -
 * @date 2018/12/13 上午10:45
 **/
@ActivityScope
@Component(modules = LoginModule.class, dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);
}