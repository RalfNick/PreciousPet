package com.ralf.module_news.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_news.entity.CollectionEntity;
import com.ralf.module_news.entity.NewsDetailEntity;
import com.ralf.module_news.mvp.contract.PicturePreviewContract;
import com.ralf.pet_provider.http.WebObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PicturePreviewPresenter
 * @email -
 * @date 2019/06/01 14:03
 **/
@ActivityScope
public class PicturePreviewPresenter extends BasePresenter<PicturePreviewContract.Model, PicturePreviewContract.View> {

    @Inject
    RxErrorHandler mHandler;

    @Inject
    public PicturePreviewPresenter(PicturePreviewContract.Model model, PicturePreviewContract.View rootView) {
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

    public void getPhotoData(int id) {
        mModel.getPhotoData(String.valueOf(id))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<NewsDetailEntity>(mHandler) {
                    @Override
                    protected void onSuccess(NewsDetailEntity data) {
                        if (data != null) {
                            mRootView.updateView(data);
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

    public void requestDownLoadImg(String url) {

    }

    public void requestCollection(int id, int wish) {
        mModel.requestCollection(id,wish)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<CollectionEntity>(mHandler) {
                    @Override
                    protected void onSuccess(CollectionEntity data) {
                        if (data != null) {
                            mRootView.updateCollectionView(data);
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
