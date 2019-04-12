package com.ralf.module_user.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.jess.arms.utils.StringUtils;
import com.ralf.module_user.entity.PetDetailEntity;
import com.ralf.module_user.entity.PetRecordEntity;
import com.ralf.module_user.mvp.contact.PetContract;
import com.ralf.pet_provider.http.WebObserver;
import com.ralf.pet_provider.user.constant.UserConstant;
import com.ralf.pet_provider.util.DateUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MasterPresenter
 * @email -
 * @date 2019/01/16 下午1:54
 **/
public class PetPresenter extends BasePresenter<PetContract.Model, PetContract.View> {

    private static final String TIME_MONTH = "%s月";

    @Inject
    RxErrorHandler mErrorHandler;

    private int mTotalPage;
    private int mCurrentPage = 1;

    @Inject
    public PetPresenter(PetContract.Model model, PetContract.View rootView) {
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
     * 获取用户宠物列表
     *
     * @param petId     用户 id
     * @param isRefresh 刷新还是加载
     */
    public void getPetData(int petId, boolean isRefresh) {
        if (isRefresh) {
            mCurrentPage = 1;
        } else {
            mCurrentPage++;
            if (mCurrentPage > mTotalPage) {
                mRootView.finishLoadMoreWithNoData();
                return;
            }
        }
        mModel.getPetData(petId, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<PetDetailEntity<PetRecordEntity>>(mErrorHandler) {
                    @Override
                    protected void onSuccess(PetDetailEntity<PetRecordEntity> data) {
                        if (data != null) {
                            mTotalPage = data.getPages();
                            if (isRefresh) {
                                mRootView.finishRefreshData(data);
                            } else {
                                mRootView.finishLoadMore(data);
                            }
                        } else {
                            mRootView.finishRefreshData(null);
                        }
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                    }
                });
    }

    /**
     * 数据处理，如果是自己的详情，添加一个可以发布状态的 子布局
     *
     * @param petList 宠物列表，Adapter 的 list
     * @param data    请求返回的数据
     */
    public void handleData(List<PetRecordEntity> petList, PetDetailEntity<PetRecordEntity> data) {
        boolean isMyTag = UserConstant.APP_USERID == data.getUserId();
        List<PetRecordEntity> recordList = data.getRecordList();
        if (isMyTag) {
            PetRecordEntity entity = new PetRecordEntity();
            entity.setMyTag(true);
            entity.setFirstItem(true);
            entity.setYear("");
            entity.setMonth("");
            entity.setDay("今天");
            petList.add(entity);
        }
        if (recordList != null && recordList.size() > 0) {
            for (int i = 0; i < recordList.size(); i++) {
                PetRecordEntity entity = recordList.get(i);
                entity.setMyTag(isMyTag);
                if (!isMyTag && petList.size() == 0) {
                    entity.setFirstItem(true);
                }
                setShowTime(entity);
                petList.add(entity);
            }
        }
    }

    /**
     * 设置时间信息
     *
     * @param entity 参数
     */
    private void setShowTime(PetRecordEntity entity) {
        String createTime = entity.getCreateTime();
        if (!StringUtils.isEmpty(createTime)) {
            if (DateUtil.isToday(createTime)) {
                entity.setDay("今天");
                entity.setYear("");
                entity.setMonth("");
            } else {
                entity.setDay(DateUtil.getDayStr(createTime));
                entity.setYear(DateUtil.getYearStr(createTime));
                entity.setMonth(String.format(TIME_MONTH,DateUtil.getMonthStr(createTime)));
            }
        }
    }

}
