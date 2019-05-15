package com.ralf.module_news.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_news.dg.module.RecommendModule;
import com.ralf.module_news.mvp.ui.fragment.RecommendNewFragment;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendComponent
 * @email -
 * @date 2019/05/15 19:30
 **/
@FragmentScope
@Component(modules = RecommendModule.class, dependencies = AppComponent.class)
public interface RecommendComponent {

    /**
     * dagger2 注入
     *
     * @param fragment RecommendNewFragment
     */
    void inject(RecommendNewFragment fragment);
}
