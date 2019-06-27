package com.ralf.module_service.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_service.dg.module.PetAdoptionModule;
import com.ralf.module_service.mvp.ui.activity.PetAdoptionActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlineComponent
 * @email -
 * @date 2019/06/19 18:17
 **/
@ActivityScope
@Component(modules = PetAdoptionModule.class, dependencies = AppComponent.class)
public interface PetAdoptionComponent {

    /**
     * dagger 注入
     *
     * @param activity PetAdoptionActivity
     */
    void inject(PetAdoptionActivity activity);
}
