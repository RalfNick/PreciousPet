package com.ralf.module_service.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_service.dg.module.SameCityModule;
import com.ralf.module_service.mvp.ui.activity.SameCityActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlineComponent
 * @email -
 * @date 2019/06/19 18:17
 **/
@ActivityScope
@Component(modules = SameCityModule.class, dependencies = AppComponent.class)
public interface SameCityComponent {

    /**
     * dagger 注入
     *
     * @param activity SameCityActivity
     */
    void inject(SameCityActivity activity);
}
