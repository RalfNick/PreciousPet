package com.ralf.module_community.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_community.entity.result.LatestInfoResultEntity;
import com.ralf.module_community.mvp.contact.LatestInfoContract;
import com.ralf.pet_provider.http.WebObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LatestInfoPresenter
 * @email -
 * @date 2019/04/13 下午4:35
 **/
@ActivityScope
public class LatestInfoPresenter extends BasePresenter<LatestInfoContract.Model, LatestInfoContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    private int mTotalPage;
    private int mCurrentPage = 1;

    @Inject
    public LatestInfoPresenter(LatestInfoContract.Model model, LatestInfoContract.View rootView) {
        super(model, rootView);
    }

    public void getLatestData(boolean isRefresh) {
        if (isRefresh) {
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
            if (mCurrentPage > mTotalPage) {
                mRootView.finishLoadMoreWithNoData();
                return;
            }
        }
        mModel.getLatestData(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<LatestInfoResultEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(LatestInfoResultEntity data) {
                        if (data != null) {
                            mTotalPage = data.getPages();
                            if (isRefresh) {
                                mRootView.finishRefresh(data.getLatestList());
                            } else {
                                mRootView.finishLoadMore(data.getLatestList());
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
