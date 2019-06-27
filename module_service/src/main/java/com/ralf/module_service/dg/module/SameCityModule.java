package com.ralf.module_service.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_service.mvp.contract.SameCityContract;
import com.ralf.module_service.mvp.model.SameCityModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlineModule
 * @email -
 * @date 2019/06/19 18:17
 **/
@Module
public class SameCityModule {

    private SameCityContract.View mView;

    public SameCityModule(SameCityContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public SameCityContract.View provideSameCityView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public SameCityContract.Model provideSameCityModel(SameCityModel model) {
        return model;
    }
}
