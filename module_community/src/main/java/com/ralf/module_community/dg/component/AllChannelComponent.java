package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.dg.module.AllChannelModule;
import com.ralf.module_community.mvp.ui.activity.AllChannelActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedComponent
 * @email -
 * @date 2018/12/17 上午10:06
 **/
@ActivityScope
@Component(modules = AllChannelModule.class, dependencies = AppComponent.class)
public interface AllChannelComponent {
    /**
     * Dagger2 注入
     *
     * @param activity AllChannelActivity
     */
    void inject(AllChannelActivity activity);
}
