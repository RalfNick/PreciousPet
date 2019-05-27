package com.ralf.module_news.dg.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_news.mvp.contract.NewsContract;
import com.ralf.module_news.mvp.contract.NewsVideoContract;
import com.ralf.module_news.mvp.model.NewsModel;

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
public class NewsVideoModule {

    private NewsVideoContract.View mView;

    public NewsVideoModule(NewsVideoContract.View view) {
        mView = view;
    }

    @FragmentScope
    @Provides
    public NewsVideoContract.View provideNewsVideoView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public NewsContract.Model provideNewsModel(NewsModel model) {
        return model;
    }
}
