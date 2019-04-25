package com.ralf.module_community.mvp.contract;

import android.app.Activity;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.result.ChannelResultEntity;
import com.ralf.pet_provider.http.BaseEntity;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendContract
 * @email -
 * @date 2019/04/13 上午10:31
 **/
public interface ChannelContract {

    interface View extends IView {

        /**
         * 加载后无数据
         */
        void finishLoadMoreWithNoData();

        /**
         * 刷新频道数据
         *
         * @param data 数据
         */
        void finishRefresh(ChannelResultEntity data);

        /**
         * 加载频道数据
         *
         * @param data 数据
         */
        void finishLoadMore(ChannelResultEntity data);

        /**
         * 获取当前的 Activity
         *
         * @return
         */
        Activity getActivity();
    }

    interface Model extends IModel {

        /**
         * 请求频道数据
         *
         * @param currentPage 当前页码
         * @return
         */
        Observable<BaseEntity<ChannelResultEntity>> getChannelData(int currentPage);
    }
}
