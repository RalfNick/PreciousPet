package com.ralf.module_news.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_news.dg.module.NewsPictureModule;
import com.ralf.module_news.mvp.ui.fragment.NewsPictureFragment;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendComponent
 * @email -
 * @date 2019/05/15 19:30
 **/
@FragmentScope
@Component(modules = NewsPictureModule.class, dependencies = AppComponent.class)
public interface NewsPictureComponent {

    /**
     * dagger2 注入
     *
     * @param fragment NewsPictureFragment
     */
    void inject(NewsPictureFragment fragment);
}
