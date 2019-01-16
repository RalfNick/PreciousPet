package com.ralf.module_user.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_user.mvp.contact.UserStateContact;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name UserStatePresenter
 * @email -
 * @date 2019/01/16 下午1:54
 **/
public class UserStatePresenter extends BasePresenter<UserStateContact.Model,UserStateContact.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public UserStatePresenter(UserStateContact.Model model, UserStateContact.View rootView) {
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
