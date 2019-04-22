package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.mvp.contract.FriendPraiseContract;
import com.ralf.module_community.mvp.model.FriendPraiseModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseModule
 * @email -
 * @date 2019/04/15 上午11:34
 **/
@Module
public class FriendPraiseModule {

    private FriendPraiseContract.View mView;

    public FriendPraiseModule(FriendPraiseContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public FriendPraiseContract.View provideFriendPraiseView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public FriendPraiseContract.Model provideFriendPraiseModel(FriendPraiseModel model) {
        return model;
    }
}
