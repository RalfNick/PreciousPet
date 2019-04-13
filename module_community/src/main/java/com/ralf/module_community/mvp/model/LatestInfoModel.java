package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.result.LatestInfoResultEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contact.LatestInfoContract;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LatestInfoModel
 * @email -
 * @date 2019/04/13 下午4:38
 **/
public class LatestInfoModel extends BaseModel implements LatestInfoContract.Model {

    @Inject
    public LatestInfoModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity<LatestInfoResultEntity>> getLatestData(int page) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.PAGE, page);
        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getLatestInfoData(object);
    }
}
