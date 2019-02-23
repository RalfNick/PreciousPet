package com.ralf.module_community.mvp.contact;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
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
 * @name FeaturedContact
 * @email -
 * @date 2018/12/17 上午10:07
 **/
public interface FeaturedContact {

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
         * 点赞结束后，使可以继续点赞或者取消点赞，请求过程中不能点赞操作，结束后恢复
         */
        void resetPraiseState();
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
    }
}
