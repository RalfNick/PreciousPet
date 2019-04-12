package com.ralf.module_user.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_user.dg.module.PetModule;
import com.ralf.module_user.mvp.ui.PetInfoActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MasterComponent
 * @email -
 * @date 2019/01/16 下午1:47
 **/
@ActivityScope
@Component(modules = PetModule.class, dependencies = AppComponent.class)
public interface PetComponent {

    /**
     * dagger2 注入
     *
     * @param activity PetInfoActivity
     */
    void inject(PetInfoActivity activity);
}
