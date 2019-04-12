package com.ralf.module_community.mvp.contact;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.PetInfoEntity;
import com.ralf.module_community.entity.result.PetListResultEntity;
import com.ralf.pet_provider.http.BaseEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetListContract
 * @email -
 * @date 2019/04/09 上午8:12
 **/
public interface PetListContract {

    interface View extends IView {

        /**
         * 刷新数据
         *
         * @param data 数据
         */
        void finishRefreshData(List<PetInfoEntity> data);
    }

    interface Model extends IModel {

        /**
         * 请求获取宠物列表
         *
         * @param userId 用户 id
         * @return
         */
        Observable<BaseEntity<PetListResultEntity>> getPetDataList(int userId);
    }
}
