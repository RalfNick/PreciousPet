package com.ralf.module_news.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_news.dg.module.NewsImportantModule;
import com.ralf.module_news.mvp.ui.fragment.NewsImportantFragment;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendComponent
 * @email -
 * @date 2019/05/15 19:30
 **/
@FragmentScope
@Component(modules = NewsImportantModule.class, dependencies = AppComponent.class)
public interface NewsImportantComponent {

    /**
     * dagger2 注入
     *
     * @param fragment NewsImportantFragment
     */
    void inject(NewsImportantFragment fragment);
}
