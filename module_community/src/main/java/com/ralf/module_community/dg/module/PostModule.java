package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.mvp.contract.PostContract;
import com.ralf.module_community.mvp.model.PostModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PostModule
 * @email -
 * @date 2019/04/25 下午5:39
 **/
@Module
public class PostModule {

    private PostContract.View mView;

    public PostModule(PostContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public PostContract.View providePostView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public PostContract.Model providePostModel(PostModel model) {
        return model;
    }
}
