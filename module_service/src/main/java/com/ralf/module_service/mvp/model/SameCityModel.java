package com.ralf.module_service.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_service.mvp.contract.SameCityContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name SameCityModel
 * @email -
 * @date 2019/06/19 18:30
 **/
@ActivityScope
public class SameCityModel extends BaseModel implements SameCityContract.Model {

    @Inject
    public SameCityModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}