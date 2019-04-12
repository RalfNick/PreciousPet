package com.ralf.module_user.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_user.mvp.contact.MasterContract;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MasterPresenter
 * @email -
 * @date 2019/01/16 下午1:54
 **/
public class MasterPresenter extends BasePresenter<MasterContract.Model,MasterContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public MasterPresenter(MasterContract.Model model, MasterContract.View rootView) {
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
