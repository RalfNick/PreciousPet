package com.ralf.pet_provider.router;

/**
 * @author Ralf(wanglixin)
 * ARouter的参数名字常量和路径管理类
 * @name RouterConfig
 * @email -
 * @date 2018/12/03 下午3:47
 **/
public final class RouterConfig {

    /**
     * provider 模块，公共
     */
    public static class Provider {

        /**
         * 传递图片的地址-Key
         */
        public static final String KEY_PIC_URLS = "pic_urls";

        /**
         * 传递图片的地址的第一张的索引-Key
         */
        public static final String KEY_PIC_INDEX = "pic_current_index";
    }

    public static class AppModule {

        /**
         * 主界面
         */
        public static final String MAIN_PATH = "/app/main";
    }

    public static class LoginRegisterModule {

        /**
         * 测试界面
         */
        public static final String MAIN_PATH = "/login_module/main";

        /**
         * 入口，跳转到登录和注册
         */
        public static final String ENTRANCE_PATH = "/login_module/entrance";
        public static final String KEY_LOGOUT = "key_logout";
        public static final String VALUE_LOGOUT = "value_logout";

        /**
         * 登录
         */
        public static final String LOGIN_PATH = "/login_module/login";

        /**
         * 注册
         */
        public static final String REGISTER_PATH = "/login_module/register";

        /**
         * 设置用户信息（注册）
         */
        public static final String REGISTER_USER_PATH = "/login_module/register_user";

        /**
         * 跳转到用户信息设置界面携带的参数 KEY
         */
        public static final String KEY_USER_PHONE = "key_user_phone";
        public static final String KEY_USER_PASSWORD = "key_user_password";

        /**
         * 用户协议
         */
        public static final String USER_PROTOCOL_PATH = "/login_module/protocol";

        /**
         * 用户协议 URL
         */
        public static final String KEY_USER_PROTOCOL_URL = "key_protocol_url";
        public static final String KEY_USER_PROTOCOL_TITLE = "key_protocol_title";

        /**
         * 修改用户密码
         */
        public static final String MODIFY_PASSWORD_PATH = "/login_module/modify_password";

    }

    /**
     * 社区模块
     */
    public static class CommunityModule {

        /**
         * ARouter 跳转携带的参数的 Key,用户 id，昵称，来自的 id
         */
        public static final String KEY_USER_ID = "userId";
        public static final String KEY_NICK_NAME = "nickName";
        public static final String KEY_FROM_USER_ID = "fromId";

        public static final String FRAGMENT_NAME = "CommunityFragment";
        /**
         * 测试界面
         */
        public static final String MAIN_PATH = "/community_module/main";

        /**
         * 社区 Fragment 主界面
         */
        public static final String COMMUNITY_PATH = "/community_module/community";

        /**
         * 社区评论详情界面
         */
        public static final String COMMUNITY_COMMENT_PATH = "/community_module/comment";

        /**
         * 点赞列表页面
         */
        public static final String COMMUNITY_PRAISE_LIST_PATH = "/community_module/praise_list";
    }

    /**
     * 资讯模块
     */
    public static class NewsModule {

        public static final String FRAGMENT_NAME = "CommunityFragment";

        /**
         * 测试界面
         */
        public static final String MAIN_PATH = "/news_module/main";

        public static final String NEWS_PATH = "/news_module/news";
    }

    /**
     * 服务模块
     */
    public static class ServiceModule {

        public static final String FRAGMENT_NAME = "CommunityFragment";

        /**
         * 测试界面
         */
        public static final String MAIN_PATH = "/service_module/main";

        public static final String SERVICE_PATH = "/service_module/service";
    }

    /**
     * 商城模块
     */
    public static class MallModule {

        public static final String FRAGMENT_NAME = "CommunityFragment";

        /**
         * 测试界面
         */
        public static final String MAIN_PATH = "/mall_module/main";

        public static final String MALL_PATH = "/mall_module/mall";
    }

    /**
     * 用户模块
     */
    public static class UserModule {

        public static final String FRAGMENT_NAME = "UserFragment";

        public static final String KEY_USER_ID = "user_id";

        /**
         * 测试界面
         */
        public static final String MAIN_PATH = "/user_module/main";

        /**
         * 用户中心
         */
        public static final String USER_PATH = "/user_module/user_center";

        /**
         * 用户状态信息
         */
        public static final String MASTER_INFO_PATH = "/user_module/master_info";
    }
}
