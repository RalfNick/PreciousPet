package com.ralf.module_community.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_community.mvp.contact.CommunityContact;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommunityPresenter
 * @email -
 * @date 2018/12/17 上午10:09
 **/
@FragmentScope
public class CommunityPresenter extends BasePresenter<CommunityContact.Model, CommunityContact.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public CommunityPresenter(CommunityContact.Model model, CommunityContact.View rootView) {
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
