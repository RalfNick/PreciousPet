package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.dg.module.LatestInfoModule;
import com.ralf.module_community.mvp.ui.activity.LatestInfoActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LatestInfoComponent
 * @email -
 * @date 2019/04/13 下午4:35
 **/
@ActivityScope
@Component(modules = LatestInfoModule.class, dependencies = AppComponent.class)
public interface LatestInfoComponent {

    /**
     * dagger2 注入
     *
     * @param activity LatestInfoActivity
     */
    void inject(LatestInfoActivity activity);
}
