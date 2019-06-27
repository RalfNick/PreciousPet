package com.ralf.module_service.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_service.mvp.contract.ExpertOnlineContract;
import com.ralf.module_service.mvp.model.ExpertOnlineModel;

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
public class ExpertOnlineModule {

    private ExpertOnlineContract.View mView;

    public ExpertOnlineModule(ExpertOnlineContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public ExpertOnlineContract.View provideExpertOnlineView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public ExpertOnlineContract.Model provideExpertOnlineModel(ExpertOnlineModel model) {
        return model;
    }
}
