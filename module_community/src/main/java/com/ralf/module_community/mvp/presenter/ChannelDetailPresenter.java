package com.ralf.module_community.mvp.presenter;

import android.support.annotation.NonNull;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_community.entity.ChannelAttentionEntity;
import com.ralf.module_community.entity.ChannelDetailEntity;
import com.ralf.module_community.mvp.contract.ChannelDetailContract;
import com.ralf.pet_provider.http.WebObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelDetailPresenter
 * @email -
 * @date 2019/04/24 上午11:14
 **/
@ActivityScope
public class ChannelDetailPresenter extends BasePresenter<ChannelDetailContract.Model, ChannelDetailContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;
    private int mCurrentPage;
    private int mTotalPages;

    @Inject
    public ChannelDetailPresenter(ChannelDetailContract.Model model, ChannelDetailContract.View rootView) {
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
     * 获取频道详情
     *
     * @param channelId 频道 id
     * @param isRefresh 是否是刷新
     * @param userId    用户 id
     */
    public void getChannelDetail(int channelId, boolean isRefresh, int userId) {
        mCurrentPage = isRefresh ? 1 : mCurrentPage + 1;
        if (!isRefresh && mCurrentPage > mTotalPages) {
            mRootView.onFinishLoadMoreWithNoData();
            return;
        }
        mModel.getChannelDetail(userId, channelId, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<ChannelDetailEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(ChannelDetailEntity data) {
                        mTotalPages = data == null ? 0 : data.getPageCount();
                        if (data != null) {
                            if (isRefresh) {
                                mRootView.onFinishFresh(data);
                            } else {
                                if (mCurrentPage < mTotalPages) {
                                    mRootView.onFinishLoadData(data);
                                } else {
                                    mRootView.onFinishLoadMoreWithNoData();
                                }
                            }
                        } else {
                            mRootView.onFinishLoadMoreWithNoData();
                        }
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                        mRootView.onFinishLoadMoreWithNoData();
                    }
                });

    }

    /**
     * 关注频道或者取消关注
     *
     * @param relType
     * @param relevantId
     * @param rightOrcancel
     * @param userId
     */
    public void addAttentionOfChannel(@NonNull int relType,
                                      @NonNull int relevantId,
                                      @NonNull int rightOrcancel,
                                      @NonNull int userId) {
        mModel.addAttentionOfChannel(relType, relevantId, rightOrcancel, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<ChannelAttentionEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(ChannelAttentionEntity data) {
                        if (data != null) {
                            mRootView.onFinishAttention(data);
                        } else {
                            mRootView.showMessage("关注失败");
                        }
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                    }
                });
    }
}
