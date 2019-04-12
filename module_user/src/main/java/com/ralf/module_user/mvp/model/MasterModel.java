package com.ralf.module_user.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_user.mvp.contact.MasterContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MasterModel
 * @email -
 * @date 2019/01/16 下午1:53
 **/
@ActivityScope
public class MasterModel extends BaseModel implements MasterContract.Model{

    @Inject
    public MasterModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
