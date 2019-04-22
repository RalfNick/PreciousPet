package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.mvp.contract.CommentContract;
import com.ralf.module_community.mvp.model.CommentModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommentModule
 * @email -
 * @date 2019/01/15 上午10:07
 **/
@Module
public class CommentModule {

    private CommentContract.View mView;

    public CommentModule(CommentContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public CommentContract.View provideCommentView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public CommentContract.Model provideCommentModel(CommentModel model) {
        return model;
    }
}
