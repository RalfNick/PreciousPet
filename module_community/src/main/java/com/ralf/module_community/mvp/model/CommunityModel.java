package com.ralf.module_community.mvp.model;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.mvp.contact.CommunityContact;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommunityModel
 * @email -
 * @date 2018/12/17 上午10:09
 **/
@FragmentScope
public class CommunityModel extends BaseModel implements CommunityContact.Model {

    @Inject
    public CommunityModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
