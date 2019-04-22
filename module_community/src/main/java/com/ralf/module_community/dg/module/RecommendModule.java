package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.mvp.contract.RecommendContract;
import com.ralf.module_community.mvp.model.RecommendModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendModule
 * @email -
 * @date 2019/04/13 上午10:31
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
