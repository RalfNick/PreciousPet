package com.ralf.module_news.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_news.dg.module.NewsDetailModule;
import com.ralf.module_news.mvp.ui.activity.NewsDetailActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsDetailComponent
 * @email -
 * @date 2019/05/27 19:37
 **/
@ActivityScope
@Component(modules = NewsDetailModule.class,dependencies = AppComponent.class)
public interface NewsDetailComponent {

    /**
     * dagger2 注入
     *
     * @param activity NewsDetailActivity
     */
    void inject(NewsDetailActivity activity);
}
