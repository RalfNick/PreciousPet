package com.ralf.module_login_register.mvp.contact;

import android.app.Activity;

import com.jess.arms.mvp.IView;
import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RegisterUserContact
 * @email -
 * @date 2018/12/12 上午9:48
 **/
public interface RegisterUserContact {

    interface View extends IView {

        /**
         * 跳转到登录界面
         */
        void jumpToLoginPage();

        Activity getActivity();

        RxPermissions getPermissons();
    }
}
