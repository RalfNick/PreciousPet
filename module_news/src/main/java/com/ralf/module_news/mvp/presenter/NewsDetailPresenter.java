package com.ralf.module_news.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_news.entity.NewsDetailEntity;
import com.ralf.module_news.mvp.contract.NewsDetailContract;
import com.ralf.pet_provider.http.WebObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsDetailPresenter
 * @email -
 * @date 2019/05/27 19:34
 **/
@ActivityScope
public class NewsDetailPresenter extends BasePresenter<NewsDetailContract.Model, NewsDetailContract.View> {

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

    /**
     * 获取新闻详情
     *
     * @param newsId    id
     * @param isRefresh 是否是刷新
     */
    public void getNewsDetailData(String newsId, boolean isRefresh) {
        mModel.getNewsDetailData(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<NewsDetailEntity>(mRxErrorHandler) {
                    @Override
                    protected void onSuccess(NewsDetailEntity data) {
                        if (data != null) {
                            mRootView.setHeaderView(data.getUrl());
                        } else {
                            mRootView.hideLoading();
                        }
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.hideLoading();
                        mRootView.showMessage(failMsg);
                    }
                });
    }
}
