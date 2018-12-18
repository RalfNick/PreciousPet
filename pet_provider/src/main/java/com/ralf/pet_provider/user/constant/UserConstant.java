package com.ralf.pet_provider.user.constant;

import com.ralf.pet_provider.http.HttpUrl;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name UserConstant
 * @email -
 * @date 2018/12/07 上午10:02
 **/
public final class UserConstant {

    /**
     * 暂时常量
     */
    public static String APP_IS_FIRST = "appIsFirst";
    public static String APP_NICKNAME = "";
    public static String APP_IMAGE = "";
    public static int APP_USERID = -1;
    public static String APP_SYSTEM = "android";
    public static String APP_TOKEN = "";
    public static int APP_SEX = -1;
    public static String APP_HX_USERID = "";
    public static String APP_PETTOKEN = "";
    public static String APP_LEVEL = "LV";
    public static String ERWEIMA_PREFIX = HttpUrl.ADD_FRIEND;
    public static String LOCATION_LAT = "";
    public static String LOCATION_LNG = "";
    public static String CITY_NAME = "";
    public static String PROVINCE_NAME = "";


    /**
     * 用户基本信息 key
     */
    public static String USER_TOKEN = "token";
    public static String USER_ID = "userId";
    public final static String PASSWORD = "password";
    public final static String USER_NAME = "userName";
    public static final String NICK_NAME = "nickName";
    public static final String USER_SEX = "sex";
    public final static String USER_IMAGE = "headPortrait";
    public final static String PHONE = "phone";
    public final static String VCODE_TYPE = "validCodeType";
    public final static String VALIDCODE = "validCode";
    public final static String USER_EXTEND = "userExtend";

    public final static String PLAY_VIDEO_STATUS = "playVideoStatus";
    public final static String POSITION = "position";

    /**
     * 三方属性 key
     */
    public final static String OPEN_ID = "openId";
    public final static String TOKEN_INFO = "tokenInfo";
    public final static String IMAGE = "img";


    /**
     * 宠物信息
     */
    public final static String USER_PET_ID = "userPetId";

    /**
     * 环信用户信息
     */
    public final static String HX_USER_NAME = "hxUserName";

    /**
     * 助手信息
     */
    public static int ASSISTANT_ID = 16567;
    public static String ASSISTANT_HXID = "TL_16567";
    public static String ASSISTANT_NAME = "牵宠小助手";
    public static String ASSISTANT_IMAGE = "";
}
