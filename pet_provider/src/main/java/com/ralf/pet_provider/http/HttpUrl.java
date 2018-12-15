package com.ralf.pet_provider.http;

import com.ralf.module_config_res.BuildConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HttpUrl
 * @email wanglixin@icourt.cc
 * @date 2018/10/19 上午9:54
 **/
public interface HttpUrl {

    /**
     *  app base url
     */
    String APP_URL_DOMAIN = BuildConfig.API_URL;

    /**
     * 购物 base url
     */
    String SHOP_BASE_URL = BuildConfig.SHOP_URL;

    /**
     * 用户协议地址
     */
    String LOGIN_USER_PROTOCOL = APP_URL_DOMAIN + "web/service/sa.htm";

    /**
     * 宠物隐私协议
     */
    String PET_PROTOCOL = APP_URL_DOMAIN + "web/service/pa.htm";

    public static final String MEILIBANG_API = "api/bbs/get/charmList";
    public static final String TUHAOBANG_API = "api/bbs/get/wealthRankList";
    public static final String ZIXUN_XIANGQING_API = "api/news/articleContent";
    public static final String ZIXUN_XIANGQING_SHOUCANG_API = "api/news/articleWish";
    public static final String ZIXUN_XIANGQING_BIAOQIAN_API = "api/news/label/articleList";
    public static final String ZIXUN_DIANZAN_API = "api/news/commentPraise";
    public static final String ZIXUN_PINGLUN_XIANGQING_API = "api/news/articleComment/detail";
    public static final String ZIXUN_HUIFU_API = "api/news/articleComment/reply";
    public static final String ZIXUN_PINGJIA_API = "api/news/articleComment";
    public static final String QIUZHU_XIANGQING_API = "api/service/helpDetail";
    public static final String QIUZHU_XIANGQING_GONGNENG_CAINA_API = "api/service/accept";
    public static final String QIUZHU_XIANGQING_GONGNENG_YOUYONG_API = "api/service/userful";
    public static final String QIUZHU_XIANGQING_GONGNENG_SHANCHU_API = "api/service/delete";
    public static final String QIUZHU_XIANGQING_GONGNENG_HUIFU_API = "api/service/seekHelpAnswer";
    public static final String QIUZHU_XIANGQING_GONGNENG_WODA_API = "api/service/seekHelpReply";
    public static final String QIUZHU_PINGLUN_XIANGQING_API = "api/service/MoreDialogue";
    public static final String SHOU_CANG_API = "api/service/seekHelpCollect";
    public static final String ZHUANJIA_ZHUYE_API = "api/service/expertPage";
    public static final String ZHUANJIA_ZHUYE_JIEDA_API = "api/service/seekHelpAnswerList";
    public static final String FRIENDS_LIST_API = "api/user/friendList";
    public static final String SHOP_CAR_URL = SHOP_BASE_URL + "wap/goods_cart1.htm?cv=1&platform=android&petToken=";
    public static final String SHOP_ORDER_URL = SHOP_BASE_URL + "wap/buyer/order_list.htm?cv=1&platform=android&petToken=";
    public static final String SHOP_SHOUHOU_URL  = SHOP_BASE_URL + "wap/buyer/order_return_list.htm?cv=1&platform=android&petToken=";
    public static final String SHOP_ADDRESS_URL = SHOP_BASE_URL + "wap/buyer/address.htm?cv=1&platform=android&petToken=";
    public static final String SHOP_GENGDUO_URL = SHOP_BASE_URL + "wap/buyer/center.htm?cv=1&platform=android&petToken=";
    public static final String YONGHU_TOUXIANG_XIUGAI_API = "api/user/headPortraitChange";
    public static final String YONGHU_NICHENG_XIUGAI_API = "api/user/nickNameChange";
    public static final String MY_PET_MINGXI_API = "api/user/petDatum";
    public static final String OTHER_PET_MINGXI_API = "api/user/getOrderPetInfo";
    public static final String PET_TOUXIANG_XIUGAI_API = "api/user/alterPetHeadPortrait";
    public static final String PET_NAME_XIUGAI_API = "api/user/alterPetName";
    public static final String SHOPPING_FENLEI_API = "wap/goodsclass.htm";
    public static final String SHOPPING_SOUSUO_API = "wap/search.htm";
    public static final String SHOP_RU_ZHU = APP_URL_DOMAIN + "api/service/merchantSettled?";
    public static final String ZHIYE_API = "api/user/professionList";
    public static final String XIUGAI_ZHIYE_API = "api/user/profChange";
    public static final String JIBING_ZICHA = APP_URL_DOMAIN + "web/service/dogPage";
    public static final String JIBING_BAIKE = APP_URL_DOMAIN + "web/service/racialDiseasePage";
    public static final String CHANG_SKIN = "api/user/personalityList"; // 个性装扮列表
    public static final String CHAT_SETTING = "api/user/chatSettings"; // 聊天设置
    public static final String GEREN_XIANGQING_CHANGE_BG_list = "api/user/bgImgList"; // 个人详情背景List
    public static final String GEREN_XIANGQING_CHANGE_BG = "api/user/bgImgChange"; // 个人详情页更换背景
    public static final String MESSAGE_REMIND_LIST = "api/user/messageConf"; // 消息提醒List
    public static final String MESSAGE_REMIND_CHANGE = "api/user/messageIntegration"; // 消息提醒更改
    public static final String GUIHUAN_SKIN = "api/user/personalityChange"; // 主题兑换
    public static final String MY_SKIN = "api/user/myPersonality"; // 我的主题
    public static final String XIAO_JI_QIAO = APP_URL_DOMAIN + "web/service/lightWave";
    public static final String UPDATE_APP = APP_URL_DOMAIN + "api/app/searchNewVersion";
    public static final String YANGCHONG_YEAR = "api/user/petYearChange"; // 修改养宠年限
    public static final String FAVORITE_PET = "api/user/likePetsChange"; // 修改喜欢的宠物
    public static final String XIUGAI_CITY = "api/user/areaChange"; // 修改城市
    public static final String PROVINCE_CITY_LIST = "api/user/getProvinceCity"; // 省份、城市列表
    public static final String DIANPING_XIANGQING = "api/service/businessCommentInfo"; // 点评详情
    public static final String ADD_FRIEND = APP_URL_DOMAIN + "file/downloadPage?id=";
    public static final String YONGHU_YISI_XIEYI = APP_URL_DOMAIN + "web/service/pa.htm";
    public static final String YONGHU_FUWU_XIEYI = APP_URL_DOMAIN + "web/service/sa.htm";

}
