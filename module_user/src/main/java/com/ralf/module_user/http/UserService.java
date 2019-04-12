package com.ralf.module_user.http;

import com.google.gson.JsonObject;
import com.ralf.module_user.entity.PetDetailEntity;
import com.ralf.module_user.entity.PetRecordEntity;
import com.ralf.pet_provider.http.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name UserService
 * @email -
 * @date 2019/04/11 上午11:39
 **/
public interface UserService {

    /**
     * 获取宠物详情
     *
     * @param body 参数
     * @return
     */
    @POST("api/bbs/get/petInfo")
    Observable<BaseEntity<PetDetailEntity<PetRecordEntity>>> getPetDetail(@Body JsonObject body);

}
