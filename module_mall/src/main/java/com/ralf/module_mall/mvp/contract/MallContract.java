package com.ralf.module_mall.mvp.contract;

import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;

import org.json.JSONArray;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MallContract
 * @email -
 * @date 2019/06/13 19:20
 **/
public interface MallContract {

    interface View extends IView {

        /**
         * 显示数据
         *
         * @param isRefresh
         * @param jsonArray 数据
         */
        void showDataOnView(boolean isRefresh, JSONArray jsonArray);
    }

    interface Model extends IModel {

        /**
         * 获取购物数据
         *
         * @return
         */
        Observable<ResponseBody> getMallData();
    }
}
