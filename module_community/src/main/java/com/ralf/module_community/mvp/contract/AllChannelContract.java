package com.ralf.module_community.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.AllChannelSectionEntity;
import com.ralf.module_community.entity.result.AllChannelResultEntity;
import com.ralf.pet_provider.http.BaseEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelDetailContract
 * @email -
 * @date 2019/04/24 上午11:10
 **/
public interface AllChannelContract {

    interface View extends IView {

        /**
         * 刷新数据后显示布局
         *
         * @param sectionEntityList 频道数据
         */
        void finishRefresh(List<AllChannelSectionEntity> sectionEntityList);
    }

    interface Model extends IModel {

        /**
         * 获取所有频道
         *
         * @param userId 用户 id
         * @return
         */
        Observable<BaseEntity<AllChannelResultEntity>> getAllChannelData(int userId);
    }
}
