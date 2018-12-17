package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.mvp.contact.CommunityContact;
import com.ralf.module_community.mvp.model.CommunityModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommunityModule
 * @email -
 * @date 2018/12/17 上午10:06
 **/
@Module
public class CommunityModule {

    private CommunityContact.View mView;

    public CommunityModule(CommunityContact.View view) {
        mView = view;
    }

    @FragmentScope
    @Provides
    public CommunityContact.View provideCommunityView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public CommunityContact.Model provideCommunityModel(CommunityModel model) {
        return model;
    }
}
