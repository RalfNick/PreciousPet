package com.ralf.module_login_register.mvp.contact;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.ralf.module_login_register.entity.LoginEntity;
import com.ralf.pet_provider.http.BaseEntity;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LoginContact
 * @email -
 * @date 2018/12/13 上午10:48
 **/
public interface LoginContact {

    interface View extends IView {
        /**
         * 获取 Activity
         *
         * @return
         */
        Activity getActivity();

        /**
         * 更新登录按钮状态
         *
         * @param isEnable true or false
         */
        void updateLoginBtnState(boolean isEnable);

        /**
         * 跳转到主页
         */
        void jumpToMainPage();
    }

    interface Model extends IModel {

        /**
         * 手机密码登录
         *
         * @param phone    手机
         * @param password 密码
         * @param object
         * @return
         */
        Observable<BaseEntity<LoginEntity>> loginRequest(String phone, String password, JsonObject object);

        /**
         * 登录三方
         *
         * @return
         */
        Observable<BaseEntity<LoginEntity>> loginThirdPart(@NonNull String openId,
                                                           @NonNull String nickName,
                                                           @NonNull int sex,
                                                           @NonNull String image,
                                                           @NonNull JsonElement tokenInfo,
                                                           @NonNull JsonObject json);

        /**
         * 退出登录
         *
         * @return
         */
        Observable<BaseEntity<LoginEntity>> logoutRequest();
    }
}
