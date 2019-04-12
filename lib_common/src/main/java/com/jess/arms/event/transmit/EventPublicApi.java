package com.jess.arms.event.transmit;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name EventPublicApi
 * @email
 * @date 2018/11/10 下午7:05
 **/
public class EventPublicApi {

    public interface LogoutApi extends EventModuleApi {

        /**
         * 账号在其他地方登陆后，弹出提示，跳转到登录界面
         *
         * @param path  入口地址
         * @param key   参数 key
         * @param value 参数值
         */
        void jumpToLoginPage(String path, String key, String value);
    }
}
