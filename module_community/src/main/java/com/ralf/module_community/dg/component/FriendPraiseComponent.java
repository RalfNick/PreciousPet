package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.dg.module.FriendPraiseModule;
import com.ralf.module_community.mvp.ui.activity.FriendPraiseActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseComponent
 * @email -
 * @date 2019/04/15 上午11:34
 **/
@ActivityScope
@Component(modules = FriendPraiseModule.class, dependencies = AppComponent.class)
public interface FriendPraiseComponent {

    /**
     * Dagger2 注入
     *
     * @param activity FriendPraiseActivity
     */
    void inject(FriendPraiseActivity activity);
}
