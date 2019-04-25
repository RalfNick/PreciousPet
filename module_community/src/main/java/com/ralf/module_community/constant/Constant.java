package com.ralf.module_community.constant;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name Constant
 * @email -
 * @date 2018/12/21 上午10:37
 **/
public final class Constant {

    /**
     * 网络请求常数类型 筛选类型 0 - 全部  1 - 关注  3 - 萌犬  4 - 萌猫  7 - 视频  8 - 其他
     */
    public static final int TYPE_REFRESH = 0;
    public static final int TYPE_ATTENTION = 1;
    public static final int TYPE_CUTE_DOG = 3;
    public static final int TYPE_CUTE_CAT = 4;
    public static final int TYPE_VIDEO = 7;
    public static final int TYPE_OTHER = 8;

    public final static String LAT = "lat";
    public final static String LNG = "lng";

    /**
     * 网络请求字段
     */
    public final static String ID = "id";
    public final static String DYNAMIC_ID = "dynamicId";
    public final static String COMMENT = "comment";
    public final static String DYNAMIC_TYPE = "dynamicType";
    public final static String PAGE = "page";
    public final static String TO_USER_ID = "toUserId";
    public final static String TO_PET_ID = "toPetId";
    public final static String TYPE = "type";
    public static String POST_ID = "postId";
    public final static String USER_ID = "userId";
    public final static String DATE_TIME = "dateTime";

    /**
     * 资源常量
     */
    public final static String GIF_REMOVE_PRAISE = "file:///android_asset/praise_false.gif";

    /**
     * 推荐部分类型
     */
    public static final String[] HEAD_TITLE_ARR = {"最新动态", "魅力榜", "好友赞过", "附近萌宠",
            "新宠露脸", "热赞榜", "帅哥美女", "明星红人"};

    /**
     * 频道部分文案常量，频道动态条数
     */
    public static final String CHANNEL_NUMBER_STATE = "新增%s条内容";
}
