package com.ralf.module_service.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_service.mvp.contract.PetAdoptionContract;

import javax.inject.Inject;

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
}
