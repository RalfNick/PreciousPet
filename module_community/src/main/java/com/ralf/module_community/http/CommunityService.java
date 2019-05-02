package com.ralf.module_community.http;

import com.google.gson.JsonObject;
import com.ralf.module_community.entity.AttentionEntity;
import com.ralf.module_community.entity.ChannelAttentionEntity;
import com.ralf.module_community.entity.ChannelDetailEntity;
import com.ralf.module_community.entity.ChannelPostDetailEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.entity.FeaturedEntity;
import com.ralf.module_community.entity.FeedbackEntity;
import com.ralf.module_community.entity.HeatPraiseEntity;
import com.ralf.module_community.entity.HistoryHeatPraiseEntity;
import com.ralf.module_community.entity.RecommendEntity;
import com.ralf.module_community.entity.ReplyEntity;
import com.ralf.module_community.entity.result.AllChannelResultEntity;
import com.ralf.module_community.entity.result.ChannelResultEntity;
import com.ralf.module_community.entity.result.FriendPraiseListResultEntity;
import com.ralf.module_community.entity.result.LatestInfoResultEntity;
import com.ralf.module_community.entity.result.PetListResultEntity;
import com.ralf.module_community.entity.result.PraiseListResultEntity;
import com.ralf.module_community.entity.result.ResultListEntity;
import com.ralf.pet_provider.http.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommunityService
 * @email -
 * @date 2018/12/21 下午12:38
 **/
public interface CommunityService {

    /**
     * 获取精选数据
     *
     * @param body body
     * @return
     */
    @POST("api/bbs/get/dynamicAll")
    Observable<BaseEntity<FeaturedEntity>> getFeaturedData(@Body JsonObject body);

    /**
     * 点赞请求
     *
     * @param body body
     * @return
     */
    @POST("api/bbs/praiseDynamic")
    Observable<BaseEntity<FeedbackEntity>> sendPraise(@Body JsonObject body);

    /**
     * 关注状态改变请求
     *
     * @param body body
     * @return
     */
    @POST("api/bbs/attention")
    Observable<BaseEntity<AttentionEntity>> changeAttentionState(@Body JsonObject body);

    /**
     * 频道帖子详情
     *
     * @param object body
     * @return
     */
    @POST("/api/channel/topicDetail")
    Observable<BaseEntity<ChannelPostDetailEntity>> getChannelDetailRequest(@Body JsonObject object);


    /**
     * 获取评论详情接口
     *
     * @param body 数据
     * @return
     */
    @POST("api/bbs/get/dynamicsInfo")
    Observable<BaseEntity<DynamicEntity>> getDiscussDetailRequest(@Body JsonObject body);

    /**
     * 评论回复请求
     *
     * @param body 数据
     * @return
     */
    @POST("api/bbs/add/comment")
    Observable<BaseEntity<ReplyEntity>> commentReplyRequest(@Body JsonObject body);

    /**
     * 请求获取点赞列表数据
     *
     * @param body 参数
     * @return
     */
    @POST("api/bbs/get/praiseList")
    Observable<BaseEntity<PraiseListResultEntity>> getPraiseList(@Body JsonObject body);

    /**
     * 查看更多宠物
     *
     * @param object 参数
     * @return
     */
    @POST("api/user/getHisPets")
    Observable<BaseEntity<PetListResultEntity>> getPetInfoList(@Body JsonObject object);

    /**
     * 请求推荐数据
     *
     * @param object 参数
     * @return
     */
    @POST("api/bbs/get/recommendHomePage")
    Observable<BaseEntity<RecommendEntity>> getRecommendData(@Body JsonObject object);

    /**
     * 获取最新动态
     *
     * @param object 参数
     * @return
     */
    @POST("api/bbs/get/latestList")
    Observable<BaseEntity<LatestInfoResultEntity>> getLatestInfoData(@Body JsonObject object);

    /**
     * 新宠露脸
     *
     * @param object 页码
     * @return
     */
    @POST("api/bbs/get/newPetList")
    Observable<BaseEntity<FriendPraiseListResultEntity>> getNewCutePetData(@Body JsonObject object);

    /**
     * 好友赞过
     *
     * @param object 页码
     * @return
     */
    @POST("api/bbs/get/friendPraiseList")
    Observable<BaseEntity<FriendPraiseListResultEntity>> getFriendPraiseData(@Body JsonObject object);

    /**
     * 请求日赞榜
     *
     * @param object 参数数据
     * @return
     */
    @POST("api/bbs/get/hotPraiseList")
    Observable<BaseEntity<ResultListEntity<HeatPraiseEntity>>> getHeatDayListRequest(@Body JsonObject object);

    /**
     * 请求历史热赞榜
     *
     * @param object 参数
     * @return
     */
    @POST("api/bbs/get/hotPraiseHistoryList")
    Observable<BaseEntity<ResultListEntity<HistoryHeatPraiseEntity>>> getHistoryHeatPraiseData(@Body JsonObject object);

    /**
     * 查看历史更多数据-点赞
     *
     * @param object 参数
     * @return
     */
    @POST("api/bbs/get/hotPraiseHistoryMore")
    Observable<BaseEntity<ResultListEntity<HeatPraiseEntity>>> getMoreHeatPraiseData(@Body JsonObject object);

    /**
     * 请求频道数据
     *
     * @param object 参数
     * @return
     */
    @POST("api/channel/channelHomePage")
    Observable<BaseEntity<ChannelResultEntity>> getChannelData(@Body JsonObject object);

    /**
     * 全部频道
     *
     * @param object 参数-用户 id
     * @return
     */
    @POST("/api/channel/aLLchannels")
    Observable<BaseEntity<AllChannelResultEntity>> getAllChannelData(@Body JsonObject object);

    /**
     * 获取频道详情
     *
     * @param object 参数
     * @return
     */
    @POST("/api/channel/channelDetails")
    Observable<BaseEntity<ChannelDetailEntity>> getChannelDetail(@Body JsonObject object);

    /**
     * 关注频道
     *
     * @param object 参数
     * @return
     */
    @POST("/api/channel/attOrLike")
    Observable<BaseEntity<ChannelAttentionEntity>> addAttentionOfChannel(@Body JsonObject object);
}
