package com.ralf.module_community.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.jess.arms.utils.StringUtils;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.constant.AttentionType;
import com.ralf.module_community.entity.AttentionEntity;
import com.ralf.module_community.entity.ChannelDetailEntity;
import com.ralf.module_community.entity.CommentEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.entity.FeedbackEntity;
import com.ralf.module_community.entity.PraiseEntity;
import com.ralf.module_community.entity.ReplyEntity;
import com.ralf.module_community.mvp.contract.CommentContract;
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
 * @name CommentPresenter
 * @email -
 * @date 2019/01/15 上午10:01
 **/
@ActivityScope
public class CommentPresenter extends BasePresenter<CommentContract.Model, CommentContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    /**
     * 请求当前页码和总页码
     */
    private int mCurrentPage = 1;
    private int mTotalPages = 1;

    /**
     * 当前需要发表评论的id
     */
    private int mDynamicId;
    private int mToUserId;

    @Inject
    public CommentPresenter(CommentContract.Model model, CommentContract.View rootView) {
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
     * 请求评论详情
     *
     * @param dynamicId user id
     * @param isRefresh 是否是刷新
     */
    public void getSelectedDiscussDetail(boolean isRefresh, int dynamicId) {
        mCurrentPage = isRefresh ? 1 : mCurrentPage + 1;
        if (!isRefresh && mCurrentPage > mTotalPages) {
            mRootView.onFinishLoadMoreWithNoData();
            return;
        }
        mModel.getDiscussDetailRequest(dynamicId, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<DynamicEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(DynamicEntity data) {
                        mTotalPages = data == null ? 0 : data.getPages();
                        if (isRefresh) {
                            mRootView.onRefreshDiscussView(data);
                        } else {
                            if (mCurrentPage < mTotalPages) {
                                mRootView.onFinishDiscussLoadMore(data);
                            } else {
                                mRootView.onFinishLoadMoreWithNoData();
                            }
                        }
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                    }
                });

    }

    /**
     * 请求频道点击详情
     *
     * @param isRefresh 是否刷新
     */
    public void getChannelDetailRequest(boolean isRefresh) {
        mCurrentPage = isRefresh ? 1 : mCurrentPage + 1;
        if (mCurrentPage > mTotalPages) {
            mRootView.onFinishLoadMoreWithNoData();
            return;
        }
        mModel.getChannelDetailRequest(mCurrentPage, 0, UserConstant.APP_USERID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<ChannelDetailEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(ChannelDetailEntity data) {
                        mTotalPages = data.getTotal();
                        if (isRefresh) {
                            mRootView.onRefreshChannelView(data);
                        } else {
                            if (mCurrentPage < mTotalPages) {
                                mRootView.onFinishChannelLoadMore(data);
                            } else {
                                mRootView.onFinishLoadMoreWithNoData();
                            }
                        }
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                    }
                });
    }

    /**
     * 发送评论
     *
     * @param editTextContent 评论内容
     */
    public void sendCommentContent(String editTextContent) {
        if (StringUtils.isEmpty(editTextContent)) {
            ToastUtils.showShort("评论内容不为空");
            return;
        }
        mModel.sendDiscussRequest(editTextContent, mDynamicId, mToUserId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<ReplyEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(ReplyEntity data) {
                        ReplyEntity.CommentDetailBean commentDetail = data.getCommentDetail();
                        CommentEntity commentEntity = new CommentEntity.Builder()
                                .commentId(commentDetail.getId())
                                .dynamicId(commentDetail.getDynamicId())
                                .headPortrait(UserConstant.APP_IMAGE)
                                .nickName(commentDetail.getNickName())
                                .toNickName(commentDetail.getToNickName())
                                .content(commentDetail.getContent())
                                .createTime(commentDetail.getCreateTime())
                                .userId(commentDetail.getUserId())
                                .toUserId(commentDetail.getToUserId())
                                .build();
                        mRootView.addNewComment(commentEntity);
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                    }
                });
    }

    /**
     * 设置当前评论数据
     *
     * @param dynamicId 动态 id
     * @param userId    用户 id
     */
    public void setCurrentComment(int dynamicId, int userId) {
        mDynamicId = dynamicId;
        mToUserId = userId;
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
