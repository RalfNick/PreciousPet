package com.ralf.preciouspet;

import android.support.multidex.MultiDex;

import com.ralf.module_chat.ChatHelper;
import com.ralf.pet_provider.base.PreciousApplication;
import com.ralf.preciouspet.crash.CrashHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetApplication
 * @email -
 * @date 2018/12/06 下午11:15
 **/
public class PetApplication extends PreciousApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        MultiDex.install(this);
        /**全局异常捕获**/
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        /**环信初始化*/
        ChatHelper.getInstance().init(this);
    }

    /**
     * init hotfix
     */
    private void initSophix() {

    }

    /**
     * 初始化友盟第三方平台信息
     */
    private void initPlatformConfig() {

    }
}
