package com.ralf.module_community.mvp.presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_community.entity.FeedbackEntity;
import com.ralf.module_community.entity.FriendPraiseEntity;
import com.ralf.module_community.entity.result.FriendPraiseListResultEntity;
import com.ralf.module_community.mvp.contract.FriendPraiseContract;
import com.ralf.pet_provider.constant.PetConstant;
import com.ralf.pet_provider.http.WebObserver;

import org.json.JSONException;
import org.json.JSONObject;

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
@ActivityScope
public class FriendPraisePresenter extends BasePresenter<FriendPraiseContract.Model, FriendPraiseContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    private int mTotalPage;
    private int mCurrentPage = 1;

    @Inject
    public FriendPraisePresenter(FriendPraiseContract.Model model, FriendPraiseContract.View rootView) {
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
     * 好友点赞数据
     *
     * @param isRefresh 刷新还是加载
     */
    public void getFriendPraiseData(boolean isRefresh) {
        if (isRefresh) {
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
            if (mCurrentPage > mTotalPage) {
                mRootView.finishLoadMoreWithNoData();
                return;
            }
        }
        mModel.getFriendPraiseData(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<FriendPraiseListResultEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(FriendPraiseListResultEntity data) {
                        if (data != null) {
                            mTotalPage = data.getPages();
                            if (isRefresh) {
                                mRootView.finishFriendPraiseRefresh(data.getFriendPraiseList());
                            } else {
                                mRootView.finishFriendPraiseLoadMore(data.getFriendPraiseList());
                            }
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

    /**
     * 新萌露脸数据
     *
     * @param isRefresh 刷新还是加载
     */
    public void getNewCutePetData(boolean isRefresh) {
        if (isRefresh) {
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
            if (mCurrentPage > mTotalPage) {
                mRootView.finishLoadMoreWithNoData();
                return;
            }
        }
        mModel.getNewCutePetData(mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<FriendPraiseListResultEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(FriendPraiseListResultEntity data) {
                        if (data != null) {
                            mTotalPage = data.getPages();
                            if (isRefresh) {
                                mRootView.finishNewPetRefresh(data.getNewPetList());
                            } else {
                                mRootView.finisNewPetLoadMore(data.getNewPetList());
                            }
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

    /**
     * 点赞请求
     *
     * @param dynamicId 动态 id
     * @param userId    用户 id
     * @param ownPraise 类型
     */
    public void sendPraiseRequest(int dynamicId, int userId, int ownPraise) {
        mModel.sendPraiseRequest(dynamicId, userId, ownPraise)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<FeedbackEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(FeedbackEntity data) {
                        mRootView.updatePraiseState(data);
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
    public void processPraiseResult(FeedbackEntity data, FriendPraiseEntity entity) {
        entity.setOwnPraise(data.getOwnPraise());
        int praiseTimes = entity.getBePraiseTimes();
        // 点赞
        if (data.getOwnPraise() == FeedbackEntity.STATE_PRAISE) {
            // 点赞人数加1
            entity.setBePraiseTimes(praiseTimes + 1);
        } else {
            // 点赞人数减1
            if (praiseTimes > 0) {
                entity.setBePraiseTimes(praiseTimes + 1);
            } else {
                entity.setBePraiseTimes(0);
            }
        }
    }
}
