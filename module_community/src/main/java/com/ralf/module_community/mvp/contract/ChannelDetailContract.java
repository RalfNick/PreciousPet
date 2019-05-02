package com.ralf.module_community.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.ChannelAttentionEntity;
import com.ralf.module_community.entity.ChannelDetailEntity;
import com.ralf.pet_provider.http.BaseEntity;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelDetailContract
 * @email -
 * @date 2019/04/24 上午11:10
 **/
public interface ChannelDetailContract {

    interface View extends IView {

        /**
         * 刷新后无数据
         */
        void onFinishLoadMoreWithNoData();

        /**
         * 刷新数据
         *
         * @param channelPostList 数据
         */
        void onFinishFresh(ChannelDetailEntity channelPostList);

        /**
         * 加载数据
         *
         * @param channelPostList 数据
         */
        void onFinishLoadData(ChannelDetailEntity channelPostList);

        /**
         * 关注频道完成
         *
         * @param data 数据
         */
        void onFinishAttention(ChannelAttentionEntity data);
    }

    interface Model extends IModel {

        /**
         * 获取频道详情
         *
         * @param userId      用户 id
         * @param channelId   频道 id
         * @param currentPage 页码
         * @return
         */
        Observable<BaseEntity<ChannelDetailEntity>> getChannelDetail(int userId, int channelId, int currentPage);

        /**
         * 关注频道或者取消关注频道
         *
         * @param relType
         * @param relevantId
         * @param rightOrcancel
         * @param userId
         * @return
         */
        Observable<BaseEntity<ChannelAttentionEntity>> addAttentionOfChannel(int relType, int relevantId, int rightOrcancel, int userId);
    }
}
