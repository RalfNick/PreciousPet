package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.result.AllChannelResultEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contract.AllChannelContract;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelDetailModel
 * @email -
 * @date 2019/04/24 上午11:11
 **/
@ActivityScope
public class AllChannelModel extends BaseModel implements AllChannelContract.Model {

    @Inject
    public AllChannelModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<BaseEntity<AllChannelResultEntity>> getAllChannelData(int userId) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.USER_ID, userId);
        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getAllChannelData(object);
    }
}
