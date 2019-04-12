package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.dg.module.PraiseListModule;
import com.ralf.module_community.mvp.ui.activity.PraiseListActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedComponent
 * @email -
 * @date 2018/12/17 上午10:06
 **/
@ActivityScope
@Component(modules = PraiseListModule.class, dependencies = AppComponent.class)
public interface PraiseListComponent {

    /**
     * 依赖注入
     * @param activity PraiseListActivity
     */
    void inject(PraiseListActivity activity);
}
