package com.ralf.module_community.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_community.entity.HeatPraiseEntity;
import com.ralf.module_community.entity.result.ResultListEntity;
import com.ralf.module_community.mvp.contract.HeatPraiseContract;
import com.ralf.pet_provider.http.WebObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraisePresenter
 * @email -
 * @date 2019/04/15 上午11:40
 **/
@FragmentScope
public class DayHeatPraisePresenter extends BasePresenter<HeatPraiseContract.Model, HeatPraiseContract.DayView> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public DayHeatPraisePresenter(HeatPraiseContract.Model model, HeatPraiseContract.DayView rootView) {
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

    public void getHeatPraiseData(boolean isRefresh) {
        mModel.getDayHeatPraiseData(String.valueOf(System.currentTimeMillis() / 1000))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<ResultListEntity<HeatPraiseEntity>>(mErrorHandler) {
                    @Override
                    protected void onSuccess(ResultListEntity<HeatPraiseEntity> data) {
                        if (data != null) {
                            List<HeatPraiseEntity> dataList = data.getList();
                            mRootView.refreshData(dataList);
                        } else {
                            mRootView.refreshWithNoData();
                        }
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                        mRootView.refreshWithNoData();
                    }
                });
    }
}
