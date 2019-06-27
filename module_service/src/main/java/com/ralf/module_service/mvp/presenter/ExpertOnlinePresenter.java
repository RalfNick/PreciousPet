package com.ralf.module_service.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_service.entity.ExpertOnlineEntity;
import com.ralf.module_service.entity.ExpertOnlineMultiEntity;
import com.ralf.module_service.entity.ExpertOnlineMultiType;
import com.ralf.module_service.mvp.contract.ExpertOnlineContract;
import com.ralf.pet_provider.http.WebObserver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlinePresenter
 * @email -
 * @date 2019/06/19 18:16
 **/
@ActivityScope
public class ExpertOnlinePresenter extends BasePresenter<ExpertOnlineContract.Model, ExpertOnlineContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    private int mTotalPage;
    private int mCurrentPage = 1;

    @Inject
    public ExpertOnlinePresenter(ExpertOnlineContract.Model model, ExpertOnlineContract.View rootView) {
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
     * 请求专家先关数据
     *
     * @param isRefresh   是否是刷新
     * @param medicalType 医疗类型
     * @param petType     宠物类型
     */
    public void getExpertOnlineData(boolean isRefresh, int medicalType, int petType) {
        if (isRefresh) {
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
            if (mCurrentPage > mTotalPage) {
                mRootView.finishLoadMoreWithNoData(null);
                return;
            }
        }
        mModel.getExpertOnlineData(medicalType, petType, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<ExpertOnlineEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(ExpertOnlineEntity data) {
                        if (data != null) {
                            mTotalPage = data.getSeekHelpPages();
                            if (isRefresh) {
                                if (mCurrentPage == mTotalPage) {
                                    mRootView.finishRefresh(postProcessData(isRefresh, data),false);
                                } else {
                                    mRootView.finishRefresh(postProcessData(isRefresh, data),true);
                                }
                            } else {
                                if (mCurrentPage == mTotalPage) {
                                    mRootView.finishLoadMoreWithNoData(postProcessData(isRefresh, data));
                                } else {
                                    mRootView.finishLoadMore(postProcessData(isRefresh, data));
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

    private List<ExpertOnlineMultiEntity> postProcessData(boolean isRefresh, ExpertOnlineEntity data) {
        List<ExpertOnlineMultiEntity> list = new ArrayList<>();
        if (data == null) {
            return list;
        }
        if (isRefresh) {
            ExpertOnlineMultiEntity headerEntity = new ExpertOnlineMultiEntity(ExpertOnlineMultiType.TYPE_HEADER);
            headerEntity.setData(data);
            list.add(headerEntity);
            ExpertOnlineMultiEntity optionEntity = new ExpertOnlineMultiEntity(ExpertOnlineMultiType.TYPE_OPTION);
            optionEntity.setData(data);
            list.add(optionEntity);
            ExpertOnlineMultiEntity expertEntity = new ExpertOnlineMultiEntity(ExpertOnlineMultiType.TYPE_EXPERT);
            expertEntity.setData(data);
            list.add(expertEntity);
        }
        List<ExpertOnlineEntity.SeekHelpsListBean> seekHelpsList = data.getSeekHelpsList();
        for (ExpertOnlineEntity.SeekHelpsListBean bean : seekHelpsList) {
            ExpertOnlineMultiEntity comment = new ExpertOnlineMultiEntity(ExpertOnlineMultiType.TYPE_COMMENT);
            comment.setSeekHelpsListBean(bean);
            list.add(comment);
        }
        return list;
    }
}
