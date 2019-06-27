package com.ralf.pet_provider.constant;

import com.ralf.module_config_res.BuildConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetConstant
 * @email -
 * @date 2018/12/13 下午1:41
 **/
public final class PetConstant {

    public static final String EMPTY = "";
    public static final String IMAGE_PATH_PREFIX = "precious_pet";
    public static String APP_SYSTEM = "android";
    /**
     * 第三方appKey等
     */
    public static final String WX_APP_KEY = BuildConfig.WX_APP_KEY;
    public static final String WX_APP_SECRET = BuildConfig.WX_APP_SECRET;
    public static final String QQ_APP_KEY = BuildConfig.QQ_APP_KEY;
    public static final String QQ_APP_ID = BuildConfig.QQ_APP_ID;
    public static final String WEIBO_APP_KEY = BuildConfig.WEIBO_APP_KEY;
    public static final String WEIBO_APP_SECRET = BuildConfig.WEIBO_APP_SECRET;
    public static final String WEIBO_REDIRECT_URL = BuildConfig.WEIBO_REDIRECT_URL;
    public static final String UMAPPKEY = BuildConfig.UMAPPKEY;
    public static final String UMENG_MSG_SECRET_KEY = BuildConfig.UMENG_MESSAGE_SECRET_KEY;

    /**
     * 手机常量相关,手机信号，手机版本
     */
    public final static String PHONE_MODEL = "A";
    public final static String PHONE_VERSION = "B";
    public static String ANDROID_VERSION = "";
    public static String ANDROID_MODEL = "";


    /**
     * 网络请求字段
     */
    public final static String PAGE = "page";
    public final static String CATEGORY = "category";
    public final static String USER_NAME = "userName";
    public final static String USER_IMAGE = "userImage";
    public final static String USERPET_ID = "userPetId";
    public final static String ID = "id";
    public final static String DYNAMIC_ID = "dynamicId";
    public final static String DYNAMIC_TYPE = "dynamicType";
    public final static String POSITION = "position";
    public final static String USER_ID = "userId";
    public final static String TOKEN = "token";
    public final static String TYPE = "type";
    public final static String RELEVANTID = "relevantId";
    public final static String RELEVANCEID = "relevanceId";
    public final static String FENLEI_ID = "excelId";
    public final static String PET_ID = "petId";
}
