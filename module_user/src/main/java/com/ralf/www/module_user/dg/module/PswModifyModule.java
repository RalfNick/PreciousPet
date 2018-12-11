package com.ralf.www.module_user.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.www.module_user.mvp.contact.PswModifyContact;
import com.ralf.www.module_user.mvp.model.PswModifyModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PswModifyModule
 * @email -
 * @date 2018/12/10 下午2:08
 **/
@Module
public class PswModifyModule {

    private PswModifyContact.View mView;

    public PswModifyModule(PswModifyContact.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    PswModifyContact.View provideModifyView() {
        return mView;
    }

    @ActivityScope
    @Provides
    PswModifyContact.Model provideModifyModel(PswModifyModel model) {
        return model;
    }
}
