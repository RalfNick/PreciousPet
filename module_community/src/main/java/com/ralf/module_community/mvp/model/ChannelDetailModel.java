package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.ChannelAttentionEntity;
import com.ralf.module_community.entity.ChannelDetailEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contract.ChannelDetailContract;
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
public class ChannelDetailModel extends BaseModel implements ChannelDetailContract.Model {

    @Inject
    public ChannelDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<BaseEntity<ChannelDetailEntity>> getChannelDetail(int userId, int channelId, int currentPage) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.CHANNEL_ID, channelId);
        object.addProperty(Constant.PAGE, currentPage);
        object.addProperty(Constant.USER_ID, userId);
        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getChannelDetail(object);
    }

    @Override
    public Observable<BaseEntity<ChannelAttentionEntity>> addAttentionOfChannel(int relType, int relevantId, int rightOrcancel, int userId) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.REL_TYPE, relType);
        object.addProperty(Constant.RELEVANTID, relevantId);
        object.addProperty(Constant.RIGHT_OR_CANCEL, rightOrcancel);
        object.addProperty(Constant.USER_ID, userId);
        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .addAttentionOfChannel(object);
    }
}
