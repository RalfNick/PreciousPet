package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.dg.module.PetListModule;
import com.ralf.module_community.mvp.ui.activity.PetListActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetListComponent
 * @email -
 * @date 2018/12/17 上午10:06
 **/
@ActivityScope
@Component(modules = PetListModule.class, dependencies = AppComponent.class)
public interface PetListComponent {

    /**
     * 依赖注入
     *
     * @param activity PetListActivity
     */
    void inject(PetListActivity activity);
}
