package com.ralf.module_mall.http;

import com.ralf.pet_provider.constant.PetConstant;
import com.ralf.pet_provider.http.HttpUrl;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MallService
 * @email -
 * @date 2019/06/13 16:47
 **/
public interface MallService {

    /**
     * 购物数据
     *
     * @param petToken 宠物token
     * @return
     */
    @Headers({HttpUrl.DOMAIN_NAME + HttpUrl.TAG_SHOP})
    @GET("wxapp/api/index.htm")
    Observable<ResponseBody> getMallData(@Query("petToken") String petToken);
}
