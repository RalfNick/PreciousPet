package com.ralf.www.pet_provider.http;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name HttpUrl
 * @email wanglixin@icourt.cc
 * @date 2018/10/19 上午9:54
 **/
public interface HttpUrl {

    /**
     * base url
     */
    String APP_URL_DOMAIN = com.ralf.www.module_config_res.BuildConfig.API_URL;

    /**
     * 用户协议地址
     */
    String LOGIN_USER_PROTOCOL = APP_URL_DOMAIN + "web/service/sa.htm";

//    /**
//     * 刷新用户新接口
//     *
//     * @param refreshToken
//     * @return
//     */
//    @PUT("user/api/v1/token")
//    Observable<AlphaResult<UserInfo>> refreshToken(@Query("refreshToken") String refreshToken);
}
