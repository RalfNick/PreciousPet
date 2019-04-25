package com.ralf.module_community.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_community.mvp.contract.AllChannelContract;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelDetailPresenter
 * @email -
 * @date 2019/04/24 上午11:14
 **/
@ActivityScope
public class AllChannelPresenter extends BasePresenter<AllChannelContract.Model,AllChannelContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public AllChannelPresenter(AllChannelContract.Model model, AllChannelContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void addDispose(Disposable disposable) {
        super.addDispose(disposable);
    }

}
