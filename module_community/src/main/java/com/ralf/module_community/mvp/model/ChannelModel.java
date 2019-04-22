package com.ralf.module_community.mvp.model;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.mvp.contract.ChannelContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendModel
 * @email -
 * @date 2019/04/13 上午10:33
 **/
@FragmentScope
public class ChannelModel extends BaseModel implements ChannelContract.Model {

    @Inject
    public ChannelModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
