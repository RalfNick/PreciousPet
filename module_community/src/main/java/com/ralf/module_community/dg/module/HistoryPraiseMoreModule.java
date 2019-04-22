package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.mvp.contract.HistoryPraiseMoreContract;
import com.ralf.module_community.mvp.model.HistoryPraiseMoreModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HistoryPraiseMoreModule
 * @email -
 * @date 2019/04/22 下午8:19
 **/
@Module
public class HistoryPraiseMoreModule {

    private HistoryPraiseMoreContract.View mView;

    public HistoryPraiseMoreModule(HistoryPraiseMoreContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public HistoryPraiseMoreContract.View provideHistoryPraiseMoreView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public HistoryPraiseMoreContract.Model provideHistoryPraiseMoreModel(HistoryPraiseMoreModel model) {
        return model;
    }
}
