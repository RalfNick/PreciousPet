package com.ralf.module_service.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_service.entity.ExpertOnlineEntity;
import com.ralf.module_service.entity.ExpertOnlineMultiEntity;
import com.ralf.pet_provider.http.BaseEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlineContract
 * @email -
 * @date 2019/06/19 18:14
 **/
public interface ExpertOnlineContract {

    interface View extends IView {

        /**
         * 刷新后没有更多数据
         *
         * @param resultList 数据
         */
        void finishLoadMoreWithNoData(List<ExpertOnlineMultiEntity> resultList);

        /**
         * 刷新后更新数据
         *
         * @param resultList 数据
         * @param hasMore
         */
        void finishRefresh(List<ExpertOnlineMultiEntity> resultList, boolean hasMore);

        /**
         * 加载后更新数据
         *
         * @param resultList 数据
         */
        void finishLoadMore(List<ExpertOnlineMultiEntity> resultList);
    }

    interface Model extends IModel {

        /**
         * 请求医生专家相关数据
         *
         * @param medicalType 医疗类型
         * @param petType     宠物类型
         * @param currentPage 页码
         * @return
         */
        Observable<BaseEntity<ExpertOnlineEntity>> getExpertOnlineData(int medicalType, int petType, int currentPage);
    }
}
