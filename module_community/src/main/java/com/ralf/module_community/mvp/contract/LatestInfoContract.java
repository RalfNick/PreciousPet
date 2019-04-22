package com.ralf.module_community.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.LatestInfoEntity;
import com.ralf.module_community.entity.result.LatestInfoResultEntity;
import com.ralf.pet_provider.http.BaseEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LatestInfoContract
 * @email -
 * @date 2019/04/13 下午4:36
 **/
public interface LatestInfoContract {

    interface View extends IView {

        /**
         * 无数据加载
         */
        void finishLoadMoreWithNoData();

        /**
         * 刷新数据
         *
         * @param data 数据集合
         */
        void finishRefresh(List<LatestInfoEntity> data);

        /**
         * 加载数据
         *
         * @param data 数据集合
         */
        void finishLoadMore(List<LatestInfoEntity> data);
    }

    interface Model extends IModel {

        /**
         * 请求最新动态信息
         *
         * @param page 页码
         * @return
         */
        Observable<BaseEntity<LatestInfoResultEntity>> getLatestData(int page);
    }
}
