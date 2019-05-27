package com.ralf.module_news.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_news.entity.BannerEntity;
import com.ralf.module_news.entity.NewsEntity;
import com.ralf.module_news.entity.NewsResultEntity;
import com.ralf.pet_provider.http.BaseEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendContract
 * @email -
 * @date 2019/05/15 19:32
 **/
public interface RecommendContract {

    interface View extends IView {

        /**
         * 加载后没有数据
         */
        void finishLoadMoreWithNoData();

        /**
         * 刷新后没有数据
         *
         * @param recommendList 数据
         */
        void finishRefresh(List<NewsEntity> recommendList);

        /**
         * 加载数据
         *
         * @param recommendList 数据
         */
        void finishLoadMore(List<NewsEntity> recommendList);

        /**
         * banner 数据
         *
         * @param bannerList 数据
         */
        void showBanner(List<BannerEntity> bannerList);
    }
}
