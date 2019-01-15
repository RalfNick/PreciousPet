package com.ralf.module_community.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.ralf.module_community.mvp.contact.CommentContact;
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

    private CommentContact.View mView;

    public CommentModule(CommentContact.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public CommentContact.View provideCommentView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public CommentContact.Model provideCommentModel(CommentModel model) {
        return model;
    }
}
