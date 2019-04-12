package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.result.PetListResultEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contact.PetListContract;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetListModel
 * @email -
 * @date 2019/04/09 上午8:14
 **/
@ActivityScope
public class PetListModel extends BaseModel implements PetListContract.Model {

    @Inject
    public PetListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity<PetListResultEntity>> getPetDataList(int userId) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.TO_USER_ID, userId);

        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getPetInfoList(object);
    }

    @Override
    public void onDestroy() {

    }
}
