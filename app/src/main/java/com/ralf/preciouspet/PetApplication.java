package com.ralf.preciouspet;

import android.support.multidex.MultiDex;

import com.ralf.module_chat.ChatHelper;
import com.ralf.pet_provider.base.PreciousApplication;
import com.ralf.pet_provider.constant.PetConstant;
import com.ralf.preciouspet.crash.CrashHandler;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

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
        initPlatformConfig();
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
        UMShareAPI.get(this);
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_DUM_NORMAL);
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, PetConstant.UMAPPKEY);
        PlatformConfig.setWeixin(PetConstant.WX_APP_KEY, PetConstant.WX_APP_SECRET);
        PlatformConfig.setQQZone(PetConstant.QQ_APP_ID, PetConstant.QQ_APP_KEY);
        PlatformConfig.setSinaWeibo(PetConstant.WEIBO_APP_KEY, PetConstant.WEIBO_APP_SECRET, PetConstant.WEIBO_REDIRECT_URL);
    }
}
