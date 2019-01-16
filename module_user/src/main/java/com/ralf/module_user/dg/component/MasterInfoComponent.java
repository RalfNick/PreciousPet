package com.ralf.module_user.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_user.dg.module.MasterInfoModule;
import com.ralf.module_user.mvp.ui.MasterInfoActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MasterInfoComponent
 * @email -
 * @date 2019/01/16 下午1:47
 **/
@ActivityScope
@Component(modules = MasterInfoModule.class,dependencies = AppComponent.class)
public interface MasterInfoComponent {

    void inject(MasterInfoActivity activity);
}
