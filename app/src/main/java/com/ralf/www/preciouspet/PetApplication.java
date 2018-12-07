package com.ralf.www.preciouspet;

import com.ralf.www.pet_provider.base.PreciousApplication;
import com.ralf.www.preciouspet.crash.CrashHandler;

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
