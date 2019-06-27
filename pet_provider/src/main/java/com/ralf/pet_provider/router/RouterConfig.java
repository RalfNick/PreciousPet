package com.ralf.pet_provider.router;

import com.alibaba.android.arouter.facade.annotation.Route;

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

        /**
         * webview activity 路径
         */
        public static final String WEB_VIEW_PATH = "/provider_module/web_view";

        /**
         * WEB VIEW URL KEY
         */
        public final static String KEY_WEB_URL = "param_url";
        public static final String KEY_WEB_TYPE = "type";
        public static final String KEY_WEB_TITLE = "title";
        public static final String KEY_WEB_MENU = "menu";
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

        /**
         * 频道详情页面
         */
        public static final String COMMUNITY_CHANNEL_DETAIL_PATH = "/community_module/channel_detail";

        /**
         * 帖子详情页面
         */
        public static final String COMMUNITY_POST_DETAIL_PATH = "/community_module/post_detail";
        public static final String COMMUNITY_KEY_POST_DETAIL_ID = "postId";

        /**
         * 频道详情页面携带参数 key - 频道 id
         */
        public static final String KEY_CHANNEL_DETAIL_ID = "channelId";

        /**
         * 全部频道页面
         */
        public static final String COMMUNITY_ALL_CHANNEL_PATH = "/community_module/all_channel";

        /**
         * 频道话题页面
         */
        public static final String COMMUNITY_CHANNEL_TOPIC_PATH = "/community_module/channel_topic";
        /**
         * 频道话题页面携带参数 key - 频道话题 id
         */
        public static final String KEY_CHANNEL_TOPIC_ID = "topicId";

        /**
         * 频道简介页面
         */
        public static final String COMMUNITY_CHANNEL_INFO_PATH = "/community_module/channel_info";
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

        /**
         * 资讯容器页面
         */
        public static final String NEWS_PATH = "/news_module/news";

        /**
         * 资讯详情页面
         */
        public static final String NEWS_PATH_DETAIL = NEWS_PATH + "/detail";
        /**
         * 资讯详情页面 key - 资讯 id
         */
        public static final String KEY_NEWS_ID = "newsId";

        /**
         * 图片预览页面
         */
        public static final String NEWS_PATH_PICTURE_PREVIEW = NEWS_PATH + "/picture_preview";
        /**
         * 图片预览页面 key - urls id
         */
        public static final String KEY_IMAGE_URLS = "imageUrls";
        /**
         * 图片预览页面 key -  id
         */
        public static final String KEY_IMAGE_ID = "imageId";
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

        /**
         * 路径 - 专家在线，同城服务，宠物领养，萌宠配对
         */
        public static final String PATH_EXPERT_ONLINE = "/service_module/expert_online";

        public static final String PATH_SAME_CITY = "/service_module/same_city";

        public static final String PATH_PET_ADOPTION = "/service_module/pet_adoption";

        public static final String PATH_PET_PAIR = "/service_module/pet_pair";
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