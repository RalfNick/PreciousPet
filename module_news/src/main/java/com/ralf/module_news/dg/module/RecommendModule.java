package com.ralf.module_news.dg.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_news.mvp.contract.RecommendContract;
import com.ralf.module_news.mvp.model.RecommendModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendModule
 * @email -
 * @date 2019/05/15 19:31
 **/
@Module
public class RecommendModule {

    private RecommendContract.View mView;

    public RecommendModule(RecommendContract.View view) {
        mView = view;
    }

    @FragmentScope
    @Provides
    public RecommendContract.View provideRecommendView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public RecommendContract.Model provideRecommendModel(RecommendModel model) {
        return model;
    }
}
