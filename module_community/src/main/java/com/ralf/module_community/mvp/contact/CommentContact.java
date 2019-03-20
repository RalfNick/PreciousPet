package com.ralf.module_community.mvp.contact;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.AttentionEntity;
import com.ralf.module_community.entity.ChannelDetailEntity;
import com.ralf.module_community.entity.CommentEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.entity.FeedbackEntity;
import com.ralf.module_community.entity.ReplyEntity;
import com.ralf.pet_provider.http.BaseEntity;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommentContact
 * @email -
 * @date 2019/01/15 上午10:02
 **/
public interface CommentContact {

    /**
     * 频道和评论公用一个接口
     */
    interface View extends IView {

        /**
         * 请求频道刷新视图
         *
         * @param data 数据
         */
        void onRefreshChannelView(ChannelDetailEntity data);

        /**
         * 请求评论详情后更新数据
         *
         * @param dynamicEntity 数据
         */
        void onRefreshDiscussView(DynamicEntity dynamicEntity);

        /**
         * 请求频道加载更多数据
         *
         * @param data 数据
         */
        void onFinishChannelLoadMore(ChannelDetailEntity data);

        /**
         * 请求评论加载更多数据
         *
         * @param data 数据
         */
        void onFinishDiscussLoadMore(DynamicEntity data);

        /**
         * 请求后没有更多数据
         */
        void onFinishLoadMoreWithNoData();

        /**
         * 添加评论后更新视图
         *
         * @param commentEntity 评论数据
         */
        void addNewComment(CommentEntity commentEntity);

        /**
         * 点赞请求后，更新view，和点赞列表
         *
         * @param position item 位置
         * @param entity   data
         */
        void updatePraiseView(int position, DynamicEntity entity);

        /**
         * 关注状态改变
         *
         * @param position      位置
         * @param attentionType 关注状态类型
         */
        void updateAttentionState(int position, int attentionType);

        /**
         * 展示获取奖励的 toast
         *
         * @param type     类型
         * @param valueStr 点赞奖励
         */
        void showToastOfPrize(int type, int valueStr);

    }

    interface Model extends IModel {

        /**
         * 请求频道详情
         *
         * @param page   页码
         * @param postId post id
         * @param userId 用户 id
         * @return
         */
        Observable<BaseEntity<ChannelDetailEntity>> getChannelDetailRequest(int page, int postId, int userId);

        /**
         * 请求评论详情
         *
         * @param dynamicId userid
         * @param page      页码
         * @return
         */
        Observable<BaseEntity<DynamicEntity>> getDiscussDetailRequest(int dynamicId, int page);

        /**
         * 发表评论讨论
         *
         * @param editTextContent 评论内容
         * @param dynamicId       动态 id
         * @param userId          用户 id
         * @return
         */
        Observable<BaseEntity<ReplyEntity>> sendDiscussRequest(String editTextContent, int dynamicId, int userId);

        /**
         * 点赞请求
         *
         * @param dynamicId 动态id
         * @param toUserId  被点赞人的id
         * @param type      类型
         * @return
         */
        Observable<BaseEntity<FeedbackEntity>> sendPraise(int dynamicId, int toUserId, int type);

        /**
         * 关注状态改变请求
         *
         * @param requestType 请求类型 0 关注   1 取消关注  2 好友关注  3 特别关注
         * @param toUserId    用户 id
         * @return
         */
        Observable<BaseEntity<AttentionEntity>> requestAttentionState(int requestType, int toUserId);
    }
}
