package com.ralf.module_community.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.mvp.contract.NearPetContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseModel
 * @email -
 * @date 2019/04/15 上午11:36
 **/
@ActivityScope
public class NeatPetModel extends BaseModel implements NearPetContract.Model {

    @Inject
    public NeatPetModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }
}
