package com.ralf.www.pet_provider.base;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseApplication;
import com.ralf.www.pet_provider.BuildConfig;
import com.ralf.www.pet_provider.constant.PetConstant;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

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
        initPlatformConfig();
    }

    private void initArouter() {
        //如果在debug模式下
        if (BuildConfig.DEBUG) {
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

    /**
     * 初始化友盟第三方平台信息
     */
    private void initPlatformConfig() {

        UMConfigure.setLogEnabled(true);
        if (Config.DEBUG = true) {
            UMShareAPI.get(this);
            MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_DUM_NORMAL);
            UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "");
            PlatformConfig.setWeixin(PetConstant.WX_APP_KEY, PetConstant.WX_APP_SECRET);
            PlatformConfig.setQQZone(PetConstant.QQ_APP_ID, PetConstant.QQ_APP_KEY);
            PlatformConfig.setSinaWeibo(PetConstant.WEIBO_APP_KEY, PetConstant.WEIBO_APP_SECRET, PetConstant.WEIBO_REDIRECT_URL);
        }
    }
}
