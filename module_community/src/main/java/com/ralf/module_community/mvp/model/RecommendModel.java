package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.RecommendEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contract.RecommendContract;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendModel
 * @email -
 * @date 2019/04/13 上午10:33
 **/
@FragmentScope
public class RecommendModel extends BaseModel implements RecommendContract.Model {

    @Inject
    public RecommendModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity<RecommendEntity>> getRecommendData(String lat, String lng) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.LAT, lat);
        object.addProperty(Constant.LNG, lng);
        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getRecommendData(object);
    }
}
