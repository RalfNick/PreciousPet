package com.ralf.module_community.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.HeatPraiseEntity;
import com.ralf.module_community.entity.result.ResultListEntity;
import com.ralf.pet_provider.http.BaseEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HistoryPraiseMoreContract
 * @email -
 * @date 2019/04/22 下午8:16
 **/
public interface HistoryPraiseMoreContract {

    interface View extends IView {

        /**
         * 刷新后数据数据更新到 View
         *
         * @param dataList 数据
         */
        void refreshData(List<HeatPraiseEntity> dataList);

        /**
         * 刷新后没有数据
         */
        void refreshWithNoData();
    }

    interface Model extends IModel {

        /**
         * 请求更多点赞数据
         *
         * @param dateTime 时间
         * @return
         */
        Observable<BaseEntity<ResultListEntity<HeatPraiseEntity>>> getHeatPraiseData(String dateTime);
    }
}
