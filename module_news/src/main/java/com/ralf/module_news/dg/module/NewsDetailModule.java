package com.ralf.module_news.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_news.mvp.contract.NewsDetailContract;
import com.ralf.module_news.mvp.model.NewsDetailModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsDetailModule
 * @email -
 * @date 2019/05/27 19:37
 **/
@Module
public class NewsDetailModule {

    private NewsDetailContract.View mView;

    public NewsDetailModule(NewsDetailContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public NewsDetailContract.View provideNewsDetailView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public NewsDetailContract.Model provideNewsDetailModel(NewsDetailModel model) {
        return model;
    }
}
