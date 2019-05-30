package com.ralf.module_news.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_news.entity.NewsDetailEntity;
import com.ralf.pet_provider.http.BaseEntity;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsDetailContract
 * @email -
 * @date 2019/05/27 19:31
 **/
public interface NewsDetailContract {

    interface View extends IView {

        /**
         * 显示新闻详情
         *
         * @param url 地址
         */
        void setHeaderView(String url);
    }

    interface Model extends IModel {

        /**
         * 请求资讯详情
         *
         * @param newsId id
         * @return
         */
        Observable<BaseEntity<NewsDetailEntity>> getNewsDetailData(String newsId);
    }
}
