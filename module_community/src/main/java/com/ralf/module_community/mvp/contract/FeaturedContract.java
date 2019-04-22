package com.ralf.module_community.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.AttentionEntity;
import com.ralf.module_community.entity.BannerEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.entity.FeaturedEntity;
import com.ralf.module_community.entity.FeedbackEntity;
import com.ralf.pet_provider.http.BaseEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedContract
 * @email -
 * @date 2018/12/17 上午10:07
 **/
public interface FeaturedContract {

    interface View extends IView {

        /**
         * 请求数据后更新数据
         *
         * @param isRefresh 是否刷新
         * @param data      精选数据
         */
        void updateView(boolean isRefresh, FeaturedEntity data);

        /**
         * 加载轮播图
         *
         * @param bannerEntities banner 实体类
         */
        void loadBannerView(List<BannerEntity> bannerEntities);

        /**
         * 点赞请求后，更新view，和点赞列表
         *
         * @param position item 位置
         * @param entity
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
         * 获取精选部分数据
         *
         * @param page 页码
         * @param type 类型
         * @return
         */
        Observable<BaseEntity<FeaturedEntity>> getFeaturedData(int page, int type);

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
