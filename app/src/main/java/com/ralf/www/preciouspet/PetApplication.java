package com.ralf.www.preciouspet;

import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseApplication;
import com.ralf.www.pet_provider.BuildConfig;
import com.ralf.www.pet_provider.base.PreciousApplication;
import com.ralf.www.preciouspet.crash.CrashHandler;

import me.jessyan.autosize.AutoSize;

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
//        DemoHelper.getInstance().init(this);
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
