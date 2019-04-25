package com.ralf.module_community.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.mvp.contract.PostContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelDetailModel
 * @email -
 * @date 2019/04/24 上午11:11
 **/
@ActivityScope
public class PostModel extends BaseModel implements PostContract.Model {

    @Inject
    public PostModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
