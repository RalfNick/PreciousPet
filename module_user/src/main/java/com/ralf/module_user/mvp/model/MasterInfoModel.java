package com.ralf.module_user.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_user.mvp.contact.MasterInfoContact;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MasterInfoModel
 * @email -
 * @date 2019/01/16 下午1:53
 **/
@ActivityScope
public class MasterInfoModel extends BaseModel implements MasterInfoContact.Model{

    @Inject
    public MasterInfoModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
