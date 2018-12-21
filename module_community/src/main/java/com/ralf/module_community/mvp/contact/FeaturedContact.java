package com.ralf.module_community.mvp.contact;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.FeaturedEntity;
import com.ralf.pet_provider.http.BaseEntity;

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
        void updateView(FeaturedEntity data);
    }

    interface Model extends IModel {

        Observable<BaseEntity<FeaturedEntity>> getFeaturedData(int page, int type);
    }
}
