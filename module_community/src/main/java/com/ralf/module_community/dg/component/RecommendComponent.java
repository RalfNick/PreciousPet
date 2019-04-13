package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.dg.module.RecommendModule;
import com.ralf.module_community.mvp.ui.fragment.RecommendFragment;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedComponent
 * @email -
 * @date 2018/12/17 上午10:06
 **/
@FragmentScope
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {

    /**
     * Dagger2 注入
     *
     * @param fragment RecommendFragment
     */
    void inject(RecommendFragment fragment);
}
