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
         */
        void jumpToLoginPage();
    }
}
