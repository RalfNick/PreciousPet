package com.ralf.module_login_register.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_login_register.entity.VertifyCodeEntity;
import com.ralf.module_login_register.http.UserService;
import com.ralf.module_login_register.mvp.contact.RegisterContact;
import com.ralf.pet_provider.http.BaseEntity;
import com.ralf.pet_provider.user.constant.UserConstant;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RegisterModel
 * @email -
 * @date 2018/12/11 上午11:49
 **/
@ActivityScope
public class RegisterModel extends BaseModel implements RegisterContact.Model {

    @Inject
    public RegisterModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<VertifyCodeEntity> vertifyPhone(String phone, String password) {

        JsonObject object = new JsonObject();
        object.addProperty(UserConstant.PHONE, phone);
        object.addProperty(UserConstant.PASSWORD, password);

        return mRepositoryManager.obtainRetrofitService(UserService.class).vertifyPhone(object);
    }

    @Override
    public Observable<VertifyCodeEntity> getVertifyCode(String phone) {

        JsonObject object = new JsonObject();
        object.addProperty(UserConstant.PHONE, phone);
        object.addProperty(UserConstant.USER_ID, String.valueOf(-1));
        object.addProperty(UserConstant.VCODE_TYPE, 0);

        return mRepositoryManager
                .obtainRetrofitService(UserService.class).getVertifyCode(object);
    }

    @Override
    public Observable<VertifyCodeEntity> vertifyCode(String phone, String vertifyCode) {
        JsonObject object = new JsonObject();
        object.addProperty(UserConstant.PHONE, phone);
        object.addProperty(UserConstant.VALIDCODE, vertifyCode);

        return mRepositoryManager.obtainRetrofitService(UserService.class).vertifyCode(object);
    }

    @Override
    public Observable<VertifyCodeEntity> registerNewUser(String image, String nickName,
                                                         String phone, String password, int sex) {
        JsonObject object = new JsonObject();
        object.addProperty(UserConstant.USER_IMAGE,image);
        object.addProperty(UserConstant.NICK_NAME,nickName);
        object.addProperty(UserConstant.USER_SEX,sex);
        object.addProperty(UserConstant.PHONE, phone);
        object.addProperty(UserConstant.PASSWORD, password);

        return mRepositoryManager.obtainRetrofitService(UserService.class).registerNewUser(object);
    }
}
