package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.HeatPraiseEntity;
import com.ralf.module_community.entity.result.ResultListEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contract.HistoryPraiseMoreContract;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HistoryPraiseMoreModel
 * @email -
 * @date 2019/04/22 下午8:17
 **/
@ActivityScope
public class HistoryPraiseMoreModel extends BaseModel implements HistoryPraiseMoreContract.Model {

    @Inject
    public HistoryPraiseMoreModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity<ResultListEntity<HeatPraiseEntity>>> getHeatPraiseData(String dateTime) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.DATE_TIME, dateTime);
        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getMoreHeatPraiseData(object);
    }
}
