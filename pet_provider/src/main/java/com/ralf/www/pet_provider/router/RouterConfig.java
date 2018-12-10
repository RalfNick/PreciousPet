package com.ralf.www.pet_provider.router;

/**
 * @author Ralf(wanglixin)
 * ARouter的参数名字常量和路径管理类
 * @name RouterConfig
 * @email -
 * @date 2018/12/03 下午3:47
 **/
public final class RouterConfig {

    public static class AppModule{

        // 主界面
        public static final String MAIN_PATH = "/app/main";
    }

    public static class UserModule{

        // 测试界面
        public static final String MAIN_PATH = "/user_module/main";
        // 入口，跳转到登录和注册
        public static final String ENTRANCE_PATH = "/user_module/entrance";
        // 登录
        public static final String LOGIN_PATH = "/user_module/login";
        // 注册
        public static final String REGISTER_PATH = "/user_module/register";

        // 用户协议
        public static final String USER_PROTOCOL_PATH = "/user_module/protocol";
        // 用户协议 URL
        public static final String KEY_USER_PROTOCOL_URL = "key_protocol_url";

    }
}
