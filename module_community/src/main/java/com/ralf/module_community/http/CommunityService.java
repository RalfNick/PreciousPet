package com.ralf.module_community.http;

import com.google.gson.JsonObject;
import com.ralf.module_community.entity.FeaturedEntity;
import com.ralf.pet_provider.http.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommunityService
 * @email -
 * @date 2018/12/21 下午12:38
 **/
public interface CommunityService {

    /**
     * 获取精选数据
     *
     * @param body body
     * @return
     */
    @POST("api/bbs/get/dynamicAll")
    Observable<BaseEntity<FeaturedEntity>> getFeaturedData(@Body JsonObject body);

}