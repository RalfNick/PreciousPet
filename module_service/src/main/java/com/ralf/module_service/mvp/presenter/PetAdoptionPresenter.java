package com.ralf.module_service.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_service.entity.AdoptionEntity;
import com.ralf.module_service.entity.result.AdoptionResultEntity;
import com.ralf.module_service.mvp.contract.PetAdoptionContract;
import com.ralf.pet_provider.http.WebObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetAdoptionPresenter
 * @email -
 * @date 2019/06/19 18:31
 **/
@ActivityScope
public class PetAdoptionPresenter extends BasePresenter<PetAdoptionContract.Model, PetAdoptionContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;
    private int mTotalPage;
    private int mCurrentPage = 1;

    @Inject
    public PetAdoptionPresenter(PetAdoptionContract.Model model, PetAdoptionContract.View rootView) {
        super(model, rootView);
    }

    public void getAdoptionData(boolean isRefresh, String adCode, String cityCode, String lat, String lng,
                                int petTypeId, int provinceId, int sex, int sort) {
        if (isRefresh) {
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
            if (mCurrentPage > mTotalPage) {
                mRootView.finishLoadMoreWithNoMoreData(null);
                return;
            }
        }
        mModel.getAdoptionData(adCode, cityCode, lat, lng, petTypeId, provinceId, sex, sort, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<AdoptionResultEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(AdoptionResultEntity data) {
                        if (data != null) {
                            mTotalPage = data.getPages();
                            if (isRefresh) {
                                if (mCurrentPage == mTotalPage) {
                                    mRootView.finishRefresh(data.getListPetAdopt(), false);
                                } else {
                                    mRootView.finishRefresh(data.getListPetAdopt(), true);
                                }
                            } else {
                                if (mCurrentPage == mTotalPage) {
                                    mRootView.finishLoadMoreWithNoMoreData(data.getListPetAdopt());
                                } else {
                                    mRootView.finishLoadMore(data.getListPetAdopt());
                                }
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
