package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.dg.module.CommentModule;
import com.ralf.module_community.mvp.ui.activity.CommentActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommentComponent
 * @email -
 * @date 2019/01/15 上午10:06
 **/
@ActivityScope
@Component(modules = CommentModule.class, dependencies = AppComponent.class)
public interface CommentComponent {

    /**
     * 注入 CommentActivity
     *
     * @param activity CommentActivity
     */
    void inject(CommentActivity activity);
}
