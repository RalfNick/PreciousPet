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
         * 跳转到评论详情的常量 2000 由精选跳转，3000 由频道跳转
         */
        public static final int TYPE_SELECTED = 2000;
        public static final int TYPE_CHANNEL = 3000;

        /**
         * ARouter 跳转携带的参数的 Key,用户 id，昵称，来自的 id
         */
        public static final String KEY_USER_ID = "userId";
        public static final String KEY_NICK_NAME = "nickName";
        public static final String KEY_FROM_USER_ID = "fromId";
        public static final String KEY_DYNAMIC_ID = "dynamicId";
        public static final String KEY_ITEM_POSITION = "itemPosition";
        public static final String KEY_NAVIGATE_TYPE = "navigateType";
        public static final String KEY_FROM_ITEM_POSITION = "position";

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

        /**
         * 宠物列表页面
         */
        public static final String COMMUNITY_PET_LIST_PATH = "/community_module/pet_list";

        /**
         * 最新动态列表页面
         */
        public static final String COMMUNITY_LATEST_PATH = "/community_module/latest_list";

        /**
         * 好友点赞列表页面,新萌露脸公用一个界面
         */
        public static final String COMMUNITY_FRIEND_PRAISE_PATH = "/community_module/friend_praise_list";
        public static final String KEY_COMMUNITY_FRIEND_PRISE_TYPE = "typeFriendPraise";

        /**
         * 跳转类型，4000 好友点赞  5000 新萌露脸
         */
        public static final int TYPE_FRIEND_PRISE = 4000;
        public static final int TYPE_NEW_CUTE_PET = 5000;

        /**
         * 热赞榜页面
         */
        public static final String COMMUNITY_HEAT_PRAISE_PATH = "/community_module/heat_praise_list";

        /**
         * 附近萌宠页面
         */
        public static final String COMMUNITY_NEAR_PET_PATH = "/community_module/near_pet_list";

        /**
         * 历史热赞榜-更多查看页面
         */
        public static final String COMMUNITY_HISTORY_PRAISE_MORE_PATH = "/community_module/history_more_list";
        /**
         * 历史热赞榜-更多查看页面 key - dateTime
         */
        public static final String KEY_HISTORY_PRAISE_MORE_TIME = "dateTime";
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

        public static final String KEY_PET_ID = "pet_id";

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

        /**
         * 宠物状态信息
         */
        public static final String PET_INFO_PATH = "/user_module/pet_info";
    }
}