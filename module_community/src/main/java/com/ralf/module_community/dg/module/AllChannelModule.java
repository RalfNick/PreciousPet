package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.mvp.contract.AllChannelContract;
import com.ralf.module_community.mvp.model.AllChannelModel;

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
public class AllChannelModule {

    private AllChannelContract.View mView;

    public AllChannelModule(AllChannelContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public AllChannelContract.View provideAllChannelView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public AllChannelContract.Model provideAllChannelModel(AllChannelModel model) {
        return model;
    }
}
