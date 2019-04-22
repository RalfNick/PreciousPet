package com.ralf.module_community.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_community.entity.result.PraiseListResultEntity;
import com.ralf.module_community.mvp.contract.PraiseListContract;
import com.ralf.pet_provider.http.WebObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PraiseLisPresenter
 * @email -
 * @date 2019/04/08 上午8:06
 **/
@ActivityScope
public class PraiseLisPresenter extends BasePresenter<PraiseListContract.Model, PraiseListContract.View> {

    private static final String TAG = PraiseLisPresenter.class.getSimpleName();

    @Inject
    RxErrorHandler mErrorHandler;

    private int mTotalPage;
    private int mCurrentPage = 1;

    @Inject
    public PraiseLisPresenter(PraiseListContract.Model model, PraiseListContract.View rootView) {
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

    public void getDataList(int userId, boolean isRefresh, int type) {
        switch (type) {
            case 0:
                break;
            default:
                break;
        }
        if (isRefresh) {
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
            if (mCurrentPage > mTotalPage) {
                mRootView.finishLoadMoreWithNoData();
                return;
            }
        }
        mModel.getPraiseList(userId, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<PraiseListResultEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(PraiseListResultEntity data) {
                        if (data != null) {
                            mTotalPage = data.getPages();
                            if (isRefresh) {
                                mRootView.finishRefresh(data.getList());
                            } else {
                                mRootView.finishLoadMore(data.getList());
                            }
                        } else {
                            mRootView.showMessage("数据加载失败，请重试！");
                        }
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                    }
                });
    }
}
