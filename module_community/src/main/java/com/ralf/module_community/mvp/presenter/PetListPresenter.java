package com.ralf.module_community.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_community.entity.result.PetListResultEntity;
import com.ralf.module_community.entity.result.PraiseListResultEntity;
import com.ralf.module_community.mvp.contact.PetListContract;
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
public class PetListPresenter extends BasePresenter<PetListContract.Model, PetListContract.View> {

    private static final String TAG = PetListPresenter.class.getSimpleName();

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public PetListPresenter(PetListContract.Model model, PetListContract.View rootView) {
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

    public void getDataList(int userId) {
        mModel.getPetDataList(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<PetListResultEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(PetListResultEntity data) {
                        if (data != null) {
                            mRootView.finishRefreshData(data.getPetList());
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
