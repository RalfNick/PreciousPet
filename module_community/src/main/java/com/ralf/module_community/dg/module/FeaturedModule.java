package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.mvp.contact.FeaturedContact;
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

    private FeaturedContact.View mView;

    public FeaturedModule(FeaturedContact.View view) {
        mView = view;
    }

    @FragmentScope
    @Provides
    public FeaturedContact.View provideCommunityView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public FeaturedContact.Model provideCommunityModel(FeaturedModel model) {
        return model;
    }
}
