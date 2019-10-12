package com.ralf.module_service.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_service.entity.AdoptionEntity;
import com.ralf.module_service.entity.result.AdoptionResultEntity;
import com.ralf.module_service.http.ServiceService;
import com.ralf.module_service.mvp.contract.PetAdoptionContract;
import com.ralf.pet_provider.constant.PetConstant;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PetAdoptionModel
 * @email -
 * @date 2019/06/19 18:31
 **/
@ActivityScope
public class PetAdoptionModel extends BaseModel implements PetAdoptionContract.Model {

    @Inject
    public PetAdoptionModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<BaseEntity<AdoptionResultEntity>> getAdoptionData(String adCode, String cityCode, String lat, String lng, int petTypeId, int provinceId, int sex, int sort, int currentPage) {
        JsonObject object = new JsonObject();
        object.addProperty(PetConstant.AD_CODE, adCode);
        object.addProperty(PetConstant.CITY_CODE, cityCode);
        object.addProperty(PetConstant.LAT, lat);
        object.addProperty(PetConstant.LNG, lng);
        object.addProperty(PetConstant.PAGE, currentPage);
        object.addProperty(PetConstant.PET_TYPEID, petTypeId);
        object.addProperty(PetConstant.PROVINCE_ID, provinceId);
        object.addProperty(PetConstant.SEX, sex);
        object.addProperty(PetConstant.SORT, sort);
        return mRepositoryManager.obtainRetrofitService(ServiceService.class)
                .getAdoptionData(object);
    }
}
