package com.ralf.module_news.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_news.entity.NewsResultEntity;
import com.ralf.pet_provider.http.BaseEntity;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsContract
 * @email -
 * @date 2019/05/27 20:17
 **/
public interface NewsContract {

    interface View extends IView {

    }

    interface Model extends IModel {

        /**
         * 请求推荐数据
         *
         * @param category    类别
         * @param currentPage 当前页码
         * @return
         */
        Observable<BaseEntity<NewsResultEntity>> getNewsData(int category, int currentPage);
    }
}
