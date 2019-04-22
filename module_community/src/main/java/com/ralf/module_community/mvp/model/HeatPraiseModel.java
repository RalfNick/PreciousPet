package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.HeatPraiseEntity;
import com.ralf.module_community.entity.HistoryHeatPraiseEntity;
import com.ralf.module_community.entity.result.ResultListEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contract.HeatPraiseContract;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseModel
 * @email -
 * @date 2019/04/15 上午11:36
 **/
@FragmentScope
public class HeatPraiseModel extends BaseModel implements HeatPraiseContract.Model {

    @Inject
    public HeatPraiseModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity<ResultListEntity<HeatPraiseEntity>>> getDayHeatPraiseData(String time) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.DATE_TIME, time);
        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getHeatDayListRequest(object);
    }

    @Override
    public Observable<BaseEntity<ResultListEntity<HistoryHeatPraiseEntity>>> getHistoryHeatPraiseData(int currentPage) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.PAGE, currentPage);
        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getHistoryHeatPraiseData(object);
    }
}
