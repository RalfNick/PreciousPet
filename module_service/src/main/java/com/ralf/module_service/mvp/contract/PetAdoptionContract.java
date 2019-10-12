package com.ralf.module_service.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_service.entity.AdoptionEntity;
import com.ralf.module_service.entity.result.AdoptionResultEntity;
import com.ralf.pet_provider.http.BaseEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ServiceContract
 * @email -
 * @date 2019/06/16 11:49
 **/
public interface PetAdoptionContract {

    interface View extends IView {

        /**
         * 请求后加载数据，没有更多数据
         *
         * @param list 结果数据，不为 null，最后一次加载或者刷新
         * @return
         */
        void finishLoadMoreWithNoMoreData(List<AdoptionEntity> list);

        /**
         * 请求后刷新数据
         *
         * @param list    结果数据
         * @param hasMore 是否还有更多数据
         * @return
         */
        void finishRefresh(List<AdoptionEntity> list, boolean hasMore);

        /**
         * 请求后加载数据
         *
         * @param list    结果数据
         * @return
         */
        void finishLoadMore(List<AdoptionEntity> list);
    }

    interface Model extends IModel {

        /**
         * 请求领养数据
         *
         * @param adCode
         * @param cityCode
         * @param lat
         * @param lng
         * @param petTypeId
         * @param provinceId
         * @param sex
         * @param sort
         * @param currentPage
         * @return
         */
        Observable<BaseEntity<AdoptionResultEntity>> getAdoptionData(String adCode, String cityCode, String lat, String lng,
                                                                     int petTypeId, int provinceId, int sex, int sort, int currentPage);
    }
}
