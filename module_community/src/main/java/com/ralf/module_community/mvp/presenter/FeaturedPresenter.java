package com.ralf.module_community.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.constant.AttentionType;
import com.ralf.module_community.entity.AttentionEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.entity.FeaturedEntity;
import com.ralf.module_community.entity.FeedbackEntity;
import com.ralf.module_community.entity.PraiseEntity;
import com.ralf.module_community.mvp.contact.FeaturedContact;
import com.ralf.pet_provider.constant.PetConstant;
import com.ralf.pet_provider.http.WebObserver;
import com.ralf.pet_provider.user.constant.UserConstant;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedPresenter
 * @email -
 * @date 2018/12/17 上午10:09
 **/
@FragmentScope
public class FeaturedPresenter extends BasePresenter<FeaturedContact.Model, FeaturedContact.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    private int mPage = 1;
    private int mTotalPage;

    @Inject
    public FeaturedPresenter(FeaturedContact.Model model, FeaturedContact.View rootView) {
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

    /**
     * 请求数据
     *
     * @param isRefresh 是否刷新
     * @param type      类型
     */
    public void requestData(boolean isRefresh, int type) {

        if (isRefresh) {
            mPage = 1;
            getFeaturedData(isRefresh, type);
        } else {
            if (mPage < mTotalPage) {
                mPage++;
                getFeaturedData(isRefresh, type);
            }
        }
    }

    /**
     * 请求数据
     */
    private void getFeaturedData(boolean isRefresh, int type) {
        mModel.getFeaturedData(mPage, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<FeaturedEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(FeaturedEntity data) {
                        mTotalPage = data.getPages();
                        // 更新 banner
                        mRootView.loadBannerView(data.getBannerList());
                        mRootView.updateView(isRefresh, data);
                        mRootView.hideLoading();
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.hideLoading();
                        mRootView.showMessage(failMsg);
                    }
                });
    }

    /**
     * 点赞请求
     *
     * @param position item 位置
     * @param entity   实体类
     */
    public void sendPraise(final int position, @NotNull DynamicEntity entity) {
        mModel.sendPraise(entity.getDynamicId(), entity.getUserId(), entity.getOwnPraise())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<FeedbackEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(FeedbackEntity data) {
                        processPraiseResult(data, entity);
                        mRootView.updatePraiseView(position, entity);
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                    }
                });
    }

    /**
     * 处理点赞成功后结果
     *
     * @param data   请求结果
     * @param entity DynamicEntity
     */
    private void processPraiseResult(FeedbackEntity data, @NotNull DynamicEntity entity) {
        entity.setOwnPraise(data.getOwnPraise());
        int praiseTimes = entity.getBePraiseTimes();
        // 点赞
        if (data.getOwnPraise() == FeedbackEntity.STATE_PRAISE) {
            // 添加自己的头像到点赞列表首位置,点赞人数加1
            List<PraiseEntity> praiseEntityList = entity.getPraiseList();
            if (praiseEntityList == null) {
                praiseEntityList = new ArrayList<>();
            }
            PraiseEntity praiseEntity = new PraiseEntity();
            praiseEntity.setUserId(UserConstant.APP_USERID);
            praiseEntity.setHeadPortrait(UserConstant.APP_IMAGE);
            praiseEntityList.add(0, praiseEntity);
            entity.setPraiseList(praiseEntityList);
            entity.setBePraiseTimes(praiseTimes + 1);

            // 获得奖励
            if (!PetConstant.EMPTY.equals(data.getAddAward())) {
                try {
                    JSONObject object = new JSONObject(data.getAddAward());
                    int type = object.getInt("type");
                    int valueStr = object.getInt("value");
                    mRootView.showToastOfPrize(type, valueStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // 去掉自己的头像,点赞人数减1
            List<PraiseEntity> praiseEntityList = entity.getPraiseList();
            if (praiseEntityList != null) {
                for (int i = 0; i < praiseEntityList.size(); i++) {
                    PraiseEntity praiseEntity = praiseEntityList.get(i);
                    if (praiseEntity != null
                            && praiseEntity.getUserId() == UserConstant.APP_USERID) {
                        praiseEntityList.remove(i);
                        if (praiseTimes > 0) {
                            entity.setBePraiseTimes(praiseTimes - 1);
                        }
                        break;
                    }
                }
            }
        }
    }

    /**
     * 请求关注状态
     *
     * @param position
     * @param dynamicBean 参数
     */
    public void requestAttentionState(int position, DynamicEntity dynamicBean) {
        Integer attentionStatus = dynamicBean.getAttentionStatus();
        Integer toUserId = dynamicBean.getUserId();
        int requestType;
        switch (attentionStatus) {
            case AttentionType.ADD_ATTENTION:
                requestType = 0;
                break;
            case AttentionType.CANCEL_ATTENTION:
                requestType = 1;
                break;
            case AttentionType.FRIEND_ATTENTION:
                requestType = 1;
                break;
            case AttentionType.SPECIAL_ATTENTION:
                requestType = 2;
                break;
            default:
                requestType = 0;
                break;
        }
        mModel.requestAttentionState(requestType, toUserId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<AttentionEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(AttentionEntity data) {
                        mRootView.updateAttentionState(position, data.getStatus());
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                    }
                });

    }
}