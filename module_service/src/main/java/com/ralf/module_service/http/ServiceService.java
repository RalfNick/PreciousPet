package com.ralf.module_service.http;

import com.google.gson.JsonObject;
import com.ralf.module_service.entity.AdoptionEntity;
import com.ralf.module_service.entity.ExpertOnlineEntity;
import com.ralf.module_service.entity.result.AdoptionResultEntity;
import com.ralf.pet_provider.http.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ServiceService
 * @email -
 * @date 2019/06/16 11:47
 **/
public interface ServiceService {

    /**
     * 专家在线数据
     *
     * @param object 参数
     * @return
     */
    @POST("api/service/expertFirstPage")
    Observable<BaseEntity<ExpertOnlineEntity>> getExpertOnlineData(@Body JsonObject object);

    /**
     * 请求领养数据
     *
     * @param object 参数
     * @return
     */
    @POST("api/service/petAdopt")
    Observable<BaseEntity<AdoptionResultEntity>> getAdoptionData(@Body JsonObject object);
}
