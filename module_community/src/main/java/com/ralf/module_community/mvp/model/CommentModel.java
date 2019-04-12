package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.AttentionEntity;
import com.ralf.module_community.entity.ChannelDetailEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.entity.FeedbackEntity;
import com.ralf.module_community.entity.ReplyEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contact.CommentContract;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommentModel
 * @email -
 * @date 2019/01/15 上午10:04
 **/
@ActivityScope
public class CommentModel extends BaseModel implements CommentContract.Model {

    @Inject
    public CommentModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity<ChannelDetailEntity>> getChannelDetailRequest(int page, int postId, int userId) {

        JsonObject object = new JsonObject();
        object.addProperty(Constant.USER_ID, userId);
        object.addProperty(Constant.PAGE, page);
        object.addProperty(Constant.POST_ID, postId);

        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getChannelDetailRequest(object);
    }

    @Override
    public Observable<BaseEntity<DynamicEntity>> getDiscussDetailRequest(int dynamicId, int page) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.DYNAMIC_ID, dynamicId);
        object.addProperty(Constant.PAGE, page);

        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getDiscussDetailRequest(object);
    }

    @Override
    public Observable<BaseEntity<ReplyEntity>> sendDiscussRequest(String editTextContent, int dynamicId, int userId) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.COMMENT, editTextContent);
        object.addProperty(Constant.DYNAMIC_ID, dynamicId);
        object.addProperty(Constant.TO_USER_ID, userId);

        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .commentReplyRequest(object);
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
