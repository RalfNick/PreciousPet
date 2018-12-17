package com.ralf.module_community.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.dg.module.CommunityModule;
import com.ralf.module_community.mvp.ui.fragment.CommunityFragment;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommunityComponent
 * @email -
 * @date 2018/12/17 上午10:06
 **/
@FragmentScope
@Component(modules = CommunityModule.class, dependencies = AppComponent.class)
public interface CommunityComponent {

    void inject(CommunityFragment fragment);
}
