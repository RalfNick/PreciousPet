package com.ralf.module_news.dg.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_news.mvp.contract.NewsImportantContract;
import com.ralf.module_news.mvp.model.NewsImportantModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsPictureModule
 * @email -
 * @date 2019/05/15 19:41
 **/
@Module
public class NewsImportantModule {

    private NewsImportantContract.View mView;

    public NewsImportantModule(NewsImportantContract.View view) {
        mView = view;
    }

    @FragmentScope
    @Provides
    public NewsImportantContract.View provideNewsImportantView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public NewsImportantContract.Model provideNewsImportantModel(NewsImportantModel model) {
        return model;
    }
}
