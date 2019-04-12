package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.dg.module.FeaturedModule;
import com.ralf.module_community.mvp.ui.fragment.FeaturedFragment;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedComponent
 * @email -
 * @date 2018/12/17 上午10:06
 **/
@FragmentScope
@Component(modules = FeaturedModule.class, dependencies = AppComponent.class)
public interface FeaturedComponent {

    /**
     * Dagger2 注入
     *
     * @param fragment FeaturedFragment
     */
    void inject(FeaturedFragment fragment);
}
