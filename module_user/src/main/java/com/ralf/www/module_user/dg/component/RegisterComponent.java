package com.ralf.www.module_user.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.www.module_user.dg.module.RegisterModule;
import com.ralf.www.module_user.mvp.ui.RegisterActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RegisterComponent
 * @email -
 * @date 2018/12/11 上午11:53
 **/
@ActivityScope
@Component(modules = RegisterModule.class, dependencies = AppComponent.class)
public interface RegisterComponent {

    void inject(RegisterActivity activity);
}
