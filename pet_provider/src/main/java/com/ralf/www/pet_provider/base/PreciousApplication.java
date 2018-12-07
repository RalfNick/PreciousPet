package com.ralf.www.pet_provider.base;

import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseApplication;
import com.ralf.www.pet_provider.BuildConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PreciousApplication
 * @email wanglixin@icourt.cc
 * @date 2018/12/03 下午3:49
 **/
public class PreciousApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        initArouter();
    }

    private void initArouter() {
        //如果在debug模式下
        if(BuildConfig.DEBUG){
            // 打印日志,默认关闭
            ARouter.openLog();
            // 开启调试模式，默认关闭(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
            // 打印日志的时候打印线程堆栈
            ARouter.printStackTrace();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this);
    }
}
