package com.ralf.module_community.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.HeatPraiseEntity;
import com.ralf.module_community.entity.HistoryHeatPraiseEntity;
import com.ralf.module_community.entity.result.ResultListEntity;
import com.ralf.pet_provider.http.BaseEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseContract
 * @email -
 * @date 2019/04/15 上午11:32
 **/
public interface HeatPraiseContract {

    interface Model extends IModel {

        /**
         * 请求热赞榜
         *
         * @param time 时间参数-前时间
         * @return
         */
        Observable<BaseEntity<ResultListEntity<HeatPraiseEntity>>> getDayHeatPraiseData(String time);

        /**
         * 请求历史热赞榜
         *
         * @param currentPage 当前页码
         * @return
         */
        Observable<BaseEntity<ResultListEntity<HistoryHeatPraiseEntity>>> getHistoryHeatPraiseData(int currentPage);
    }

    interface DayView extends IView {

        /**
         * 刷新后数据列表
         *
         * @param dataList 数据
         */
        void refreshData(List<HeatPraiseEntity> dataList);

        /**
         * 刷新后无数据
         */
        void refreshWithNoData();
    }

    interface HistoryView extends IView {

        /**
         * 加载后无数据
         */
        void finishLoadMoreWithNoData();

        /**
         * 刷新后无数据，显示空白提示
         */
        void refreshWithNoData();

        /**
         * 刷新结束
         *
         * @param dataList 数据集
         */
        void finishRefresh(List<HistoryHeatPraiseEntity> dataList);

        /**
         * 加载结束
         *
         * @param dataList 数据
         */
        void finishLoadMore(List<HistoryHeatPraiseEntity> dataList);
    }
}
