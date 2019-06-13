package com.ralf.module_news.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_news.entity.CollectionEntity;
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
public interface PicturePreviewContract {

    interface View extends IView {

        /**
         * 更新页面
         *
         * @param data
         */
        void updateView(NewsDetailEntity data);

        /**
         * 更新收藏视图
         *
         * @param data 收藏参数
         */
        void updateCollectionView(CollectionEntity data);
    }

    interface Model extends IModel {

        /**
         * 获取图片资讯详情
         *
         * @param id id
         * @return
         */
        Observable<BaseEntity<NewsDetailEntity>> getPhotoData(String id);

        /**
         * 收藏
         *
         * @param id   图片资讯 id
         * @param wish 是否收藏
         * @return
         */
        Observable<BaseEntity<CollectionEntity>> requestCollection(int id, int wish);
    }
}
