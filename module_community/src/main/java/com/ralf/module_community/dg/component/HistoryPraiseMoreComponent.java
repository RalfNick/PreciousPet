package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.dg.module.HistoryPraiseMoreModule;
import com.ralf.module_community.mvp.ui.activity.HistoryPraiseMoreActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HistoryPraiseMoreComponent
 * @email -
 * @date 2019/04/22 下午8:18
 **/
@ActivityScope
@Component(modules = HistoryPraiseMoreModule.class, dependencies = AppComponent.class)
public interface HistoryPraiseMoreComponent {

    /**
     * Dagger2 注入
     *
     * @param activity DayHeatPraiseFragment
     */
    void inject(HistoryPraiseMoreActivity activity);
}
