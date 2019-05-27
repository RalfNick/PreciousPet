package com.ralf.module_news.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_news.entity.NewsEntity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendContract
 * @email -
 * @date 2019/05/15 19:32
 **/
public interface NewsImportantContract {

    interface View extends IView {

        /**
         * 加载后没有数据
         */
        void finishLoadMoreWithNoData();

        /**
         * 刷新后没有数据
         *
         * @param data 数据
         */
        void finishRefresh(List<NewsEntity> data);

        /**
         * 加载数据
         *
         * @param data 数据
         */
        void finishLoadMore(List<NewsEntity> data);
    }

    interface Model extends IModel {

    }
}
