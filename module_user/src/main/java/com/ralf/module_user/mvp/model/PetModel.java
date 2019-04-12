package com.ralf.module_user.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_user.entity.PetDetailEntity;
import com.ralf.module_user.entity.PetRecordEntity;
import com.ralf.module_user.http.UserService;
import com.ralf.module_user.mvp.contact.PetContract;
import com.ralf.pet_provider.http.BaseEntity;
import com.ralf.pet_provider.user.constant.UserConstant;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MasterModel
 * @email -
 * @date 2019/01/16 下午1:53
 **/
@ActivityScope
public class PetModel extends BaseModel implements PetContract.Model{

    @Inject
    public PetModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity<PetDetailEntity<PetRecordEntity>>> getPetData(int petId, int currentPage) {
        JsonObject object = new JsonObject();
        object.addProperty(UserConstant.PET_ID, petId);
        object.addProperty(UserConstant.PAGE, currentPage);

        return mRepositoryManager.obtainRetrofitService(UserService.class)
                .getPetDetail(object);
    }
}
