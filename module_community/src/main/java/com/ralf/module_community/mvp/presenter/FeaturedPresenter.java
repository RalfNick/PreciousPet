package com.ralf.module_community.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_community.mvp.contact.FeaturedContact;

import javax.inject.Inject;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedPresenter
 * @email -
 * @date 2018/12/17 上午10:09
 **/
@FragmentScope
public class FeaturedPresenter extends BasePresenter<FeaturedContact.Model, FeaturedContact.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    private int mPage = 1;

    @Inject
    public FeaturedPresenter(FeaturedContact.Model model, FeaturedContact.View rootView) {
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

    /**
     * 请求数据
     *
     * @param isRefresh 是否刷新
     * @param type      类型
     */
    public void requestData(boolean isRefresh, int type) {

    }
}
