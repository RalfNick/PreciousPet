package com.ralf.www.module_user.http;

import com.google.gson.JsonObject;
import com.ralf.www.module_user.entity.LoginEntity;
import com.ralf.www.module_user.entity.VertifyCodeEntity;
import com.ralf.www.pet_provider.http.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name UserService
 * @email -
 * @date 2018/12/10 下午2:22
 **/
public interface UserService {

    /**
     * 未登录状态下 获取验证码
     *
     * @param object json
     * @return
     */
    @POST("api/user/getValidCodeRegister")
    Observable<VertifyCodeEntity> getVertifyCode(@Body JsonObject object);

    /**
     * 修改密码请求
     *
     * @param body json
     * @return
     */
    @POST("api/user/changePassword")
    Observable<VertifyCodeEntity> requestModifyPsw(@Body JsonObject body);

    /**
     * 注册的三个步骤
     *
     * @param body json
     * @return
     */
    @POST("api/user/register/step1")
    Observable<VertifyCodeEntity> vertifyPhone(@Body JsonObject body);

    @POST("api/user/register/step2")
    Observable<VertifyCodeEntity> vertifyCode(@Body JsonObject body);

    @POST("api/user/register/step3")
    Observable<VertifyCodeEntity> registerNewUser(@Body JsonObject body);

    /**
     * 登录
     *
     * @param body json
     * @return
     */
    @POST("api/user/login")
    Observable<BaseEntity<LoginEntity>> loginRequest(@Body JsonObject body);

    /**
     * 三方登录
     *
     * @param object json
     * @return
     */
    @POST("api/user/thirdpartyLogin")
    Observable<BaseEntity<LoginEntity>> thirdPartLogin(@Body JsonObject object);

    /**
     * 退出登录
     *
     * @param object json
     * @return
     */
    @POST("api/user/Logout")
    Observable<BaseEntity<LoginEntity>> logoutRequest(@Body JsonObject object);
}
