package com.ralf.module_community.mvp.contact;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_community.entity.PraiseEntity;
import com.ralf.module_community.entity.result.ListMultipleEntity;
import com.ralf.module_community.entity.result.PraiseListResultEntity;
import com.ralf.pet_provider.http.BaseEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PraiseListContract
 * @email -
 * @date 2019/04/08 上午8:02
 **/
public interface PraiseListContract {

    interface View extends IView {

        /**
         * 没有更多数据
         */
        void finishLoadMoreWithNoData();

        /**
         * 数据刷新完毕
         *
         * @param dataBean 数据
         */
        void finishRefresh(List<PraiseEntity> dataBean);

        /**
         * 数据加载完毕
         *
         * @param data 数据
         */
        void finishLoadMore(List<PraiseEntity> data);
    }

    interface Model extends IModel {

        /**
         * 请求获取点赞列表数据
         *
         * @param userId 用户 id
         * @param page   页码
         * @return
         */
        Observable<BaseEntity<PraiseListResultEntity>> getPraiseList(int userId, int page);
    }
}
