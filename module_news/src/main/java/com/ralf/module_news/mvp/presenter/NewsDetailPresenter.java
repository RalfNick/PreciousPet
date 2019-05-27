package com.ralf.module_news.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_news.mvp.contract.NewsDetailContract;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsDetailPresenter
 * @email -
 * @date 2019/05/27 19:34
 **/
@ActivityScope
public class NewsDetailPresenter extends BasePresenter<NewsDetailContract.Model,NewsDetailContract.View> {

    @Inject
    RxErrorHandler mRxErrorHandler;

    @Inject
    public NewsDetailPresenter(NewsDetailContract.Model model, NewsDetailContract.View rootView) {
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
