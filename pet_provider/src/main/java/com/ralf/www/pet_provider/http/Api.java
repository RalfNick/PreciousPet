package com.ralf.www.pet_provider.http;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name Api
 * @email wanglixin@icourt.cc
 * @date 2018/10/19 上午9:54
 **/
public interface Api {

    String APP_URL_DOMAIN = com.ralf.www.module_config_res.BuildConfig.API_URL;

//    /**
//     * 刷新用户新接口
//     *
//     * @param refreshToken
//     * @return
//     */
//    @PUT("user/api/v1/token")
//    Observable<AlphaResult<UserInfo>> refreshToken(@Query("refreshToken") String refreshToken);
}
