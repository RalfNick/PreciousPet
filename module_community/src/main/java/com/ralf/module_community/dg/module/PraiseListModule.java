package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.mvp.contact.PraiseListContract;
import com.ralf.module_community.mvp.model.PraiseListModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PraiseListModule
 * @email -
 * @date 2019/04/08 上午8:01
 **/
@Module
public class PraiseListModule {

    private PraiseListContract.View mView;

    public PraiseListModule(PraiseListContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public PraiseListContract.View providePraiseListView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public PraiseListContract.Model providePraiseListModel(PraiseListModel model) {
        return model;
    }
}
