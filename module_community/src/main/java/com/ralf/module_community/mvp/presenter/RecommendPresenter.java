package com.ralf.module_community.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.ralf.module_community.entity.RecommendEntity;
import com.ralf.module_community.entity.RecommendSectionEntity;
import com.ralf.module_community.mvp.contract.RecommendContract;
import com.ralf.pet_provider.http.WebObserver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

import static com.ralf.module_community.constant.Constant.HEAD_TITLE_ARR;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendPresenter
 * @email -
 * @date 2019/04/13 上午10:30
 **/
@FragmentScope
public class RecommendPresenter extends BasePresenter<RecommendContract.Model, RecommendContract.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public RecommendPresenter(RecommendContract.Model model, RecommendContract.View rootView) {
        super(model, rootView);
    }

    /**
     * 获取推荐数据
     *
     * @param lat 参数一
     * @param lng 参数二
     */
    public void getRecommendData(String lat, String lng) {
        mModel.getRecommendData(lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<RecommendEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(RecommendEntity data) {
                        if (data != null) {
                            List<RecommendSectionEntity> resultList = postProcessData(data);
                            mRootView.setDataToView(resultList);
                        } else {
                            mRootView.showMessage("暂无数据！");
                        }
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                    }
                });
    }

    /**
     * 对数据进行处理，便于布局
     *
     * @param data 数据
     * @return
     */
    private List<RecommendSectionEntity> postProcessData(RecommendEntity data) {
        List<RecommendSectionEntity> resultList = new ArrayList<>();
        List<RecommendEntity.InnerBean> beauty = data.getBeauty();
        List<RecommendEntity.InnerBean> charm = data.getCharm();
        List<RecommendEntity.InnerBean> friend = data.getFriend();
        List<RecommendEntity.InnerBean> latest = data.getLatest();
        List<RecommendEntity.InnerBean> nearPet = data.getNearPet();
        List<RecommendEntity.InnerBean> newPet = data.getNewPet();
        List<RecommendEntity.InnerBean> starAndReds = data.getStarAndReds();
        List<RecommendEntity.InnerBean> praise = data.getPraise();
        int i = 0;
        buildSectionEntity(resultList, latest, i++);
        buildSectionEntity(resultList, charm, i++);
        buildSectionEntity(resultList, friend, i++);
        buildSectionEntity(resultList, nearPet, i++);
        buildSectionEntity(resultList, newPet, i++);
        buildSectionEntity(resultList, praise, i);

        return resultList;
    }

    private void buildSectionEntity(List<RecommendSectionEntity> resultList, List<RecommendEntity.InnerBean> beauty, int i) {
        if (beauty != null && beauty.size() > 0) {
            RecommendSectionEntity itemTitle = new RecommendSectionEntity(true, HEAD_TITLE_ARR[i]);
            resultList.add(itemTitle);
            for (RecommendEntity.InnerBean bean : beauty) {
                RecommendSectionEntity itemContent = new RecommendSectionEntity(bean);
                resultList.add(itemContent);
            }
        }
    }
}
