package com.ralf.module_news.dg.component;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_news.dg.module.PicturePreviewModule;
import com.ralf.module_news.mvp.ui.activity.PicturePreviewActivity;

import dagger.Component;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PicturePreviewComponent
 * @email -
 * @date 2019/06/01 14:45
 **/
@ActivityScope
@Component(modules = PicturePreviewModule.class, dependencies = AppComponent.class)
public interface PicturePreviewComponent {

    /**
     * dagger2 注入
     *
     * @param activity PicturePreviewActivity
     */
    void inject(PicturePreviewActivity activity);
}
