package com.ralf.module_service.mvp.model;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_service.mvp.contract.ServiceContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ServiceModel
 * @email -
 * @date 2019/06/16 11:50
 **/
@FragmentScope
public class ServiceModel extends BaseModel implements ServiceContract.Model {

    @Inject
    public ServiceModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
