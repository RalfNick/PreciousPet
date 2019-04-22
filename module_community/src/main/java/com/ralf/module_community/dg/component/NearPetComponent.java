package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.dg.module.NearPetModule;
import com.ralf.module_community.mvp.ui.activity.NearPetActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseComponent
 * @email -
 * @date 2019/04/15 上午11:34
 **/
@ActivityScope
@Component(modules = NearPetModule.class, dependencies = AppComponent.class)
public interface NearPetComponent {

    /**
     * Dagger2 注入
     *
     * @param activity NearPetActivity
     */
    void inject(NearPetActivity activity);
}
