package com.ralf.module_mall.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_mall.dg.module.MallModule;
import com.ralf.module_mall.mvp.ui.fragment.MallFragment;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MallComponent
 * @email -
 * @date 2019/06/13 19:24
 **/
@FragmentScope
@Component(modules = MallModule.class, dependencies = AppComponent.class)
public interface MallComponent {

    /**
     * dagger2 注入
     *
     * @param fragment MallFragment
     */
    void inject(MallFragment fragment);
}
