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

        public static final String MAIN_PATH = "/app/main";
    }

    public static class UserModule{

        public static final String MAIN_PATH = "/user_module/main";
        public static final String ENTRANCE_PATH = "/user_module/entrance";
        public static final String LOGIN_PATH = "/user_module/login";
        public static final String REGISTER_PATH = "/user_module/register";
    }
}
