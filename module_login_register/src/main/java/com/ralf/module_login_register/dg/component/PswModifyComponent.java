package com.ralf.module_login_register.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_login_register.dg.module.PswModifyModule;
import com.ralf.module_login_register.mvp.ui.PassWordModifyActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PswModifyComponent
 * @email -
 * @date 2018/12/10 下午2:07
 **/
@ActivityScope
@Component(modules = PswModifyModule.class, dependencies = AppComponent.class)
public interface PswModifyComponent {

    void inject(PassWordModifyActivity activity);
}
