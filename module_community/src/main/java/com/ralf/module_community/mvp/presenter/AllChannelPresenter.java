package com.ralf.module_community.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.AllChannelSectionEntity;
import com.ralf.module_community.entity.ChannelTypeEntity;
import com.ralf.module_community.entity.result.AllChannelResultEntity;
import com.ralf.module_community.mvp.contract.AllChannelContract;
import com.ralf.pet_provider.http.WebObserver;
import com.ralf.pet_provider.user.constant.UserConstant;

import java.util.ArrayList;
import java.util.List;

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
public class AllChannelPresenter extends BasePresenter<AllChannelContract.Model, AllChannelContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public AllChannelPresenter(AllChannelContract.Model model, AllChannelContract.View rootView) {
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

    public void getAllChannelData() {
        mModel.getAllChannelData(UserConstant.APP_USERID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<AllChannelResultEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(AllChannelResultEntity data) {
                        if (data != null) {
                            List<AllChannelSectionEntity> sectionEntityList = processDataForView(data);
                            mRootView.finishRefresh(sectionEntityList);
                        } else {
                            mRootView.showMessage("数据加载失败，请重试！");
                            mRootView.finishRefresh(new ArrayList<>());
                        }
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                        mRootView.finishRefresh(new ArrayList<>());
                    }
                });
    }

    /**
     * 处理数据，便于添加到 View 上
     *
     * @param data 我的频道和其他频道数据
     */
    private List<AllChannelSectionEntity> processDataForView(AllChannelResultEntity data) {
        List<AllChannelSectionEntity> resultList = new ArrayList<>();
        List<ChannelTypeEntity> myChannels = data.getMyChannels();
        if (myChannels != null && myChannels.size() > 0) {
            resultList.add(new AllChannelSectionEntity(true, Constant.CHANNEL_OF_MY));
            for (ChannelTypeEntity entity : myChannels) {
                AllChannelSectionEntity sectionBean = new AllChannelSectionEntity(entity);
                resultList.add(sectionBean);
            }
        }
        List<ChannelTypeEntity> otherChannel = data.getOtherChannel();
        if (otherChannel != null && otherChannel.size() > 0) {
            resultList.add(new AllChannelSectionEntity(true, Constant.CHANNEL_OF_OTHERS));
            for (ChannelTypeEntity entity : otherChannel) {
                AllChannelSectionEntity sectionBean = new AllChannelSectionEntity(entity);
                resultList.add(sectionBean);
            }
        }
        return resultList;
    }
}
