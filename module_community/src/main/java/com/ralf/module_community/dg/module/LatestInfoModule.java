package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.mvp.contract.LatestInfoContract;
import com.ralf.module_community.mvp.model.LatestInfoModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LatestInfoModule
 * @email -
 * @date 2019/04/13 下午4:36
 **/
@Module
public class LatestInfoModule {

    private LatestInfoContract.View mView;

    public LatestInfoModule(LatestInfoContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public LatestInfoContract.View provideLatestInfoView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public LatestInfoContract.Model provideLatestInfoModel(LatestInfoModel model) {
        return model;
    }
}
