package com.ralf.module_user.mvp.contact;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_user.entity.PetDetailEntity;
import com.ralf.module_user.entity.PetRecordEntity;
import com.ralf.pet_provider.http.BaseEntity;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MasterContract
 * @email -
 * @date 2019/01/16 下午1:51
 **/
public interface PetContract {

    interface Model extends IModel {

        /**
         * 获取宠物列表
         *
         * @param petId       用户 id
         * @param currentPage 页码
         * @return
         */
        Observable<BaseEntity<PetDetailEntity<PetRecordEntity>>> getPetData(int petId, int currentPage);
    }

    interface View extends IView {

        /**
         * 请求数据后刷新 View
         *
         * @param petData 宠物列表
         */
        void finishRefreshData(PetDetailEntity<PetRecordEntity> petData);

        /**
         * 加载后没有更多数据
         */
        void finishLoadMoreWithNoData();

        /**
         * 没有更多数据
         *
         * @param data 数据
         */
        void finishLoadMore(PetDetailEntity<PetRecordEntity> data);
    }
}
