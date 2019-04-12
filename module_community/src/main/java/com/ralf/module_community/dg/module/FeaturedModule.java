package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.mvp.contact.FeaturedContract;
import com.ralf.module_community.mvp.model.FeaturedModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedModule
 * @email -
 * @date 2018/12/17 上午10:06
 **/
@Module
public class FeaturedModule {

    private FeaturedContract.View mView;

    public FeaturedModule(FeaturedContract.View view) {
        mView = view;
    }

    @FragmentScope
    @Provides
    public FeaturedContract.View provideCommunityView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public FeaturedContract.Model provideCommunityModel(FeaturedModel model) {
        return model;
    }
}
