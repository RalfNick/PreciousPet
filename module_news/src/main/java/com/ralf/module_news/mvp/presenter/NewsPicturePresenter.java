package com.ralf.module_news.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_news.entity.NewsResultEntity;
import com.ralf.module_news.mvp.contract.NewsContract;
import com.ralf.module_news.mvp.contract.NewsPictureContract;
import com.ralf.pet_provider.http.WebObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendPresenter
 * @email -
 * @date 2019/05/15 19:35
 **/
@FragmentScope
public class NewsPicturePresenter extends BasePresenter<NewsContract.Model,NewsPictureContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    private int mTotalPage;
    private int mCurrentPage = 1;

    @Inject
    public NewsPicturePresenter(NewsContract.Model model, NewsPictureContract.View rootView) {
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

    /**
     * 请求图片数据
     *
     * @param type      类型 - 视频
     * @param isRefresh 是否是刷新
     */
    public void getPictureData(int type, boolean isRefresh) {
        if (isRefresh) {
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
            if (mCurrentPage > mTotalPage) {
                mRootView.finishLoadMoreWithNoData();
                return;
            }
        }
        mModel.getNewsData(type, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<NewsResultEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(NewsResultEntity data) {
                        if (data != null) {
                            mTotalPage = data.getPages();
                            if (isRefresh) {
                                mRootView.finishRefresh(data.getRecommendList());
                            } else {
                                mRootView.finishLoadMore(data.getRecommendList());
                            }
                        } else {
                            mRootView.hideLoading();
                            mRootView.showMessage("数据加载失败，请重试！");
                        }
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                        mRootView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mRootView.showMessage(e.getMessage());
                        mRootView.hideLoading();
                    }
                });
    }
}
