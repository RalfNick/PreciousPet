package com.ralf.module_service.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_service.entity.ExpertOnlineEntity;
import com.ralf.module_service.http.ServiceService;
import com.ralf.module_service.mvp.contract.ExpertOnlineContract;
import com.ralf.pet_provider.constant.PetConstant;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ExpertOnlineModel
 * @email -
 * @date 2019/06/19 18:15
 **/
@ActivityScope
public class ExpertOnlineModel extends BaseModel implements ExpertOnlineContract.Model {

    @Inject
    public ExpertOnlineModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity<ExpertOnlineEntity>> getExpertOnlineData(int medicalType, int petType, int currentPage) {
        JsonObject object = new JsonObject();
        object.addProperty(PetConstant.FENLEI_ID, medicalType);
        object.addProperty(PetConstant.PAGE, currentPage);
        object.addProperty(PetConstant.PET_ID, petType);
        return mRepositoryManager.obtainRetrofitService(ServiceService.class)
                .getExpertOnlineData(object);
    }
}
