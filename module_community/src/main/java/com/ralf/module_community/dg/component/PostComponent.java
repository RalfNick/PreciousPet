package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.dg.module.PostModule;
import com.ralf.module_community.mvp.ui.activity.PostDetailActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PostComponent
 * @email -
 * @date 2019/04/25 下午5:39
 **/
@ActivityScope
@Component(modules = PostModule.class, dependencies = AppComponent.class)
public interface PostComponent {

    /**
     * Dagger2 注入
     *
     * @param activity PostDetailActivity
     */
    void inject(PostDetailActivity activity);
}
