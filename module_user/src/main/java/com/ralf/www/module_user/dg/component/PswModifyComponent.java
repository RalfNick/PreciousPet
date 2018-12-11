package com.ralf.www.module_user.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.www.module_user.dg.module.PswModifyModule;
import com.ralf.www.module_user.mvp.ui.PassWordModifyActivity;

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
