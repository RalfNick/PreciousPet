package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.mvp.contract.ChannelContract;
import com.ralf.module_community.mvp.model.ChannelModel;

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
public class ChannelModule {

    private ChannelContract.View mView;

    public ChannelModule(ChannelContract.View view) {
        mView = view;
    }

    @FragmentScope
    @Provides
    public ChannelContract.View provideChannelView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public ChannelContract.Model provideChannelModel(ChannelModel model) {
        return model;
    }
}
