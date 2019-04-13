package com.ralf.module_community.mvp.contact;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.RecommendEntity;
import com.ralf.module_community.entity.RecommendSectionEntity;
import com.ralf.pet_provider.http.BaseEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendContract
 * @email -
 * @date 2019/04/13 上午10:31
 **/
public interface RecommendContract {

    interface View extends IView {

        /**
         * 将请求的推荐数据布置到 View 上
         *
         * @param resultList 数据集合
         */
        void setDataToView(List<RecommendSectionEntity> resultList);
    }

    interface Model extends IModel {

        /**
         * 请求推荐数据
         *
         * @param lat 参数一
         * @param lng 参数二
         * @return
         */
        Observable<BaseEntity<RecommendEntity>> getRecommendData(String lat, String lng);
    }
}
