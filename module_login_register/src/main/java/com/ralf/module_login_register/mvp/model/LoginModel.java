package com.ralf.module_login_register.mvp.model;

import android.support.annotation.NonNull;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_login_register.entity.LoginEntity;
import com.ralf.module_login_register.http.UserService;
import com.ralf.module_login_register.mvp.contact.LoginContact;
import com.ralf.pet_provider.http.BaseEntity;
import com.ralf.pet_provider.user.constant.UserConstant;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LoginModel
 * @email -
 * @date 2018/12/13 上午10:50
 **/
@ActivityScope
public class LoginModel extends BaseModel implements LoginContact.Model {

    @Inject
    public LoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity<LoginEntity>> loginRequest(String phone, String password, JsonObject jsonObject) {

        JsonObject object = new JsonObject();
        object.addProperty(UserConstant.PHONE, phone);
        object.addProperty(UserConstant.PASSWORD, password);
        object.add(UserConstant.USER_EXTEND, jsonObject);

        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .loginRequest(object);
    }

    @Override
    public Observable<BaseEntity<LoginEntity>> loginThirdPart(@NonNull String openId,
                                                              @NonNull String nickName,
                                                              @NonNull int sex,
                                                              @NonNull String image,
                                                              @NonNull JsonElement tokenInfo,
                                                              @NonNull JsonObject json) {

        JsonObject object = new JsonObject();
        object.addProperty(UserConstant.OPEN_ID, openId);
        object.addProperty(UserConstant.NICK_NAME, nickName);
        object.addProperty(UserConstant.USER_SEX, sex);
        object.addProperty(UserConstant.IMAGE, image);
        object.add(UserConstant.TOKEN_INFO, tokenInfo);
        object.add(UserConstant.USER_EXTEND, json);

        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .thirdPartLogin(object);
    }

    @Override
    public Observable<BaseEntity<LoginEntity>> logoutRequest() {

        JsonObject object = new JsonObject();
        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .logoutRequest(object);
    }

}
