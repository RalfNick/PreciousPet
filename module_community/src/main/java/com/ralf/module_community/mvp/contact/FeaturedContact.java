package com.ralf.module_community.mvp.contact;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.BannerEntity;
import com.ralf.module_community.entity.FeaturedEntity;
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
         * @param data 精选数据
         */
        void updateView(boolean isRefresh,FeaturedEntity data);

        /**
         * 加载轮播图
         *
         * @param bannerEntities banner 实体类
         */
        void loadBannerView(List<BannerEntity> bannerEntities);
    }

    interface Model extends IModel {

        Observable<BaseEntity<FeaturedEntity>> getFeaturedData(int page, int type);
    }
}
