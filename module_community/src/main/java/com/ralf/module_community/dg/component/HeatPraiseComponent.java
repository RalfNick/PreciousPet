package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.dg.module.HeatPraiseModule;
import com.ralf.module_community.mvp.ui.activity.HeatPraiseActivity;
import com.ralf.module_community.mvp.ui.fragment.DayHeatPraiseFragment;
import com.ralf.module_community.mvp.ui.fragment.HistoryHeatPraiseFragment;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseComponent
 * @email -
 * @date 2019/04/15 上午11:34
 **/
@FragmentScope
@Component(modules = HeatPraiseModule.class, dependencies = AppComponent.class)
public interface HeatPraiseComponent {

    /**
     * Dagger2 注入
     *
     * @param fragment DayHeatPraiseFragment
     */
    void inject(DayHeatPraiseFragment fragment);

    /**
     * Dagger2 注入
     *
     * @param fragment HistoryHeatPraiseFragment
     */
    void inject(HistoryHeatPraiseFragment fragment);
}
