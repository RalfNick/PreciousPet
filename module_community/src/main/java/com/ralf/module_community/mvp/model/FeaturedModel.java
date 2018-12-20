package com.ralf.module_community.mvp.model;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.mvp.contact.FeaturedContact;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedModel
 * @email -
 * @date 2018/12/17 上午10:09
 **/
@FragmentScope
public class FeaturedModel extends BaseModel implements FeaturedContact.Model {

    @Inject
    public FeaturedModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
