package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.mvp.contract.ChannelDetailContract;
import com.ralf.module_community.mvp.model.ChannelDetailModel;

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
public class ChannelDetailModule {

    private ChannelDetailContract.View mView;

    public ChannelDetailModule(ChannelDetailContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public ChannelDetailContract.View provideChannelDetailView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public ChannelDetailContract.Model provideChannelDetailModel(ChannelDetailModel model) {
        return model;
    }
}
