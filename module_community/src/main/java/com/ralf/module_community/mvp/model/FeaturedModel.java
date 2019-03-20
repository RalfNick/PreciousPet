package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.AttentionEntity;
import com.ralf.module_community.entity.FeaturedEntity;
import com.ralf.module_community.entity.FeedbackEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contact.FeaturedContact;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedModel
 * @email -
 * @date 2018/12/17 上午10:09
 **/
@FragmentScope
public class FeaturedModel extends BaseModel implements FeaturedContact.Model {

    @Inject
    public FeaturedModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity<FeaturedEntity>> getFeaturedData(int page, int type) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.DYNAMIC_TYPE, type);
        object.addProperty(Constant.PAGE, page);

        return mRepositoryManager.obtainRetrofitService(CommunityService.class).getFeaturedData(object);
    }

    @Override
    public Observable<BaseEntity<FeedbackEntity>> sendPraise(int dynamicId, int toUserId, int type) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.DYNAMIC_ID, dynamicId);
        object.addProperty(Constant.TO_USER_ID, toUserId);
        object.addProperty(Constant.TYPE, type);

        return mRepositoryManager.obtainRetrofitService(CommunityService.class).sendPraise(object);
    }

    @Override
    public Observable<BaseEntity<AttentionEntity>> requestAttentionState(int requestType, int toUserId) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.TYPE, requestType);
        object.addProperty(Constant.TO_USER_ID, toUserId);

        return mRepositoryManager.obtainRetrofitService(CommunityService.class).changeAttentionState(object);
    }
}
