package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.FeedbackEntity;
import com.ralf.module_community.entity.result.FriendPraiseListResultEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contract.FriendPraiseContract;
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
@ActivityScope
public class FriendPraiseModel extends BaseModel implements FriendPraiseContract.Model {

    @Inject
    public FriendPraiseModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity<FriendPraiseListResultEntity>> getFriendPraiseData(int currentPage) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.PAGE, currentPage);
        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getFriendPraiseData(object);
    }

    @Override
    public Observable<BaseEntity<FriendPraiseListResultEntity>> getNewCutePetData(int currentPage) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.PAGE, currentPage);
        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getNewCutePetData(object);
    }

    @Override
    public Observable<BaseEntity<FeedbackEntity>> sendPraiseRequest(int dynamicId, int toUserId, int type) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.DYNAMIC_ID, dynamicId);
        object.addProperty(Constant.TO_USER_ID, toUserId);
        object.addProperty(Constant.TYPE, type);

        return mRepositoryManager.obtainRetrofitService(CommunityService.class).sendPraise(object);
    }
}
