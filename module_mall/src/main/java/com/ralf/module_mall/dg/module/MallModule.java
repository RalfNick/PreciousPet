package com.ralf.module_mall.dg.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_mall.mvp.contract.MallContract;
import com.ralf.module_mall.mvp.model.MallModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MallModule
 * @email -
 * @date 2019/06/13 19:25
 **/
@Module
public class MallModule {

    private MallContract.View mView;

    public MallModule(MallContract.View view) {
        mView = view;
    }

    @FragmentScope
    @Provides
    public MallContract.View provideMallView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public MallContract.Model provideMallModel(MallModel model) {
        return model;
    }
}
