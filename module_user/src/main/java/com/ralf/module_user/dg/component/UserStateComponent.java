package com.ralf.module_user.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_user.dg.module.UserStateModule;
import com.ralf.module_user.mvp.ui.UserStateInfoActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name UserStateComponent
 * @email -
 * @date 2019/01/16 下午1:47
 **/
@ActivityScope
@Component(modules = UserStateModule.class,dependencies = AppComponent.class)
public interface UserStateComponent {

    void inject(UserStateInfoActivity activity);
}
