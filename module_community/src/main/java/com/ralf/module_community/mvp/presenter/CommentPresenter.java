package com.ralf.module_community.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_community.mvp.contact.CommentContact;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommentPresenter
 * @email -
 * @date 2019/01/15 上午10:01
 **/
@ActivityScope
public class CommentPresenter extends BasePresenter<CommentContact.Model,CommentContact.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public CommentPresenter(CommentContact.Model model, CommentContact.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mErrorHandler = null;
    }
}
