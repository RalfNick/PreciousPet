package com.ralf.module_community.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.FeedbackEntity;
import com.ralf.module_community.entity.FriendPraiseEntity;
import com.ralf.module_community.entity.result.FriendPraiseListResultEntity;
import com.ralf.pet_provider.http.BaseEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseContract
 * @email -
 * @date 2019/04/15 上午11:32
 **/
public interface FriendPraiseContract {

    interface Model extends IModel {

        /**
         * 获取好友点赞数据
         *
         * @param currentPage 页数
         * @return
         */
        Observable<BaseEntity<FriendPraiseListResultEntity>> getFriendPraiseData(int currentPage);

        /**
         * 获取新萌露脸数据
         *
         * @param currentPage 页数
         * @return
         */
        Observable<BaseEntity<FriendPraiseListResultEntity>> getNewCutePetData(int currentPage);

        /**
         * 点赞请求
         *
         * @param dynamicId 动态 id
         * @param toUserId  用户 id
         * @param type      类型
         * @return
         */
        Observable<BaseEntity<FeedbackEntity>> sendPraiseRequest(int dynamicId, int toUserId, int type);
    }

    interface View extends IView {

        /**
         * 加载后没有数据
         */
        void finishLoadMoreWithNoData();

        /**
         * 新萌露脸数据刷新
         *
         * @param newPetList 数据
         */
        void finishNewPetRefresh(List<FriendPraiseEntity> newPetList);

        /**
         * 新萌露脸数据加载
         *
         * @param newPetList 数据
         */
        void finisNewPetLoadMore(List<FriendPraiseEntity> newPetList);

        /**
         * 好友点赞数据刷新
         *
         * @param friendPraiseList 数据
         */
        void finishFriendPraiseRefresh(List<FriendPraiseEntity> friendPraiseList);

        /**
         * 好友点赞数据刷新
         *
         * @param friendPraiseList 数据
         */
        void finishFriendPraiseLoadMore(List<FriendPraiseEntity> friendPraiseList);

        /**
         * 显示点赞奖励
         *
         * @param type     类型
         * @param valueStr 奖励值
         */
        void showToastOfPrize(int type, int valueStr);

        /**
         * 更新点赞状态
         *
         * @param data 数据
         */
        void updatePraiseState(FeedbackEntity data);
    }
}
