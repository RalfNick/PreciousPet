package com.ralf.module_user.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_user.mvp.contact.UserStateContact;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name UserStateModel
 * @email -
 * @date 2019/01/16 下午1:53
 **/
@ActivityScope
public class UserStateModel extends BaseModel implements UserStateContact.Model{

    @Inject
    public UserStateModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
