package com.ralf.module_community.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_community.entity.HeatPraiseEntity;
import com.ralf.module_community.entity.result.ResultListEntity;
import com.ralf.module_community.mvp.contract.HistoryPraiseMoreContract;
import com.ralf.pet_provider.http.WebObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HistoryPraiseMorePresenter
 * @email -
 * @date 2019/04/22 下午8:21
 **/
@ActivityScope
public class HistoryPraiseMorePresenter extends BasePresenter<HistoryPraiseMoreContract.Model, HistoryPraiseMoreContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public HistoryPraiseMorePresenter(HistoryPraiseMoreContract.Model model, HistoryPraiseMoreContract.View rootView) {
        super(model, rootView);
    }

    /**
     * 请求点赞数据
     *
     * @param isRefresh 是否刷新
     * @param dateTime  日期
     */
    public void getHeatPraiseData(boolean isRefresh, String dateTime) {
        mModel.getHeatPraiseData(dateTime)
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
