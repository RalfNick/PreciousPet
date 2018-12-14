package com.ralf.www.module_user.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.www.module_user.dg.module.LoginModule;
import com.ralf.www.module_user.mvp.ui.LoginActivity;

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
