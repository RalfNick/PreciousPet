package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.dg.module.ChannelDetailModule;
import com.ralf.module_community.mvp.ui.activity.ChannelActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedComponent
 * @email -
 * @date 2018/12/17 上午10:06
 **/
@ActivityScope
@Component(modules = ChannelDetailModule.class, dependencies = AppComponent.class)
public interface ChannelDetailComponent {

    /**
     * Dagger2 注入
     *
     * @param activity ChannelActivity
     */
    void inject(ChannelActivity activity);
}
