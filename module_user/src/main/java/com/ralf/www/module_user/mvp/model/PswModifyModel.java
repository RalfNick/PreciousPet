package com.ralf.www.module_user.mvp.model;

import android.support.annotation.NonNull;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.www.module_user.entity.VertifyCodeEntity;
import com.ralf.www.module_user.http.UserService;
import com.ralf.www.module_user.mvp.contact.PswModifyContact;
import com.ralf.www.pet_provider.http.BaseEntity;
import com.ralf.www.pet_provider.user.constant.UserConstant;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PswModifyModel
 * @email -
 * @date 2018/12/10 下午2:12
 **/
@ActivityScope
public class PswModifyModel extends BaseModel implements PswModifyContact.Model {

    @Inject
    public PswModifyModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<VertifyCodeEntity> getVertifyCode(String phone, String userId, @NonNull int type) {

        JsonObject object = new JsonObject();
        object.addProperty(UserConstant.PHONE, phone);
        object.addProperty(UserConstant.USER_ID, -1);
        object.addProperty(UserConstant.VCODE_TYPE, type);

        return mRepositoryManager
                .obtainRetrofitService(UserService.class)
                .getVertifyCode(object);
    }

    @Override
    public Observable<VertifyCodeEntity> requestModifyPsw(String phone, String passWord, String vertifyCode) {

        JsonObject object = new JsonObject();
        object.addProperty(UserConstant.PHONE, phone);
        object.addProperty(UserConstant.PASSWORD, passWord);
        object.addProperty(UserConstant.VALIDCODE, vertifyCode);

        return mRepositoryManager
                .obtainRetrofitService(UserService.class)
                .requestModifyPsw(object);
    }
}
