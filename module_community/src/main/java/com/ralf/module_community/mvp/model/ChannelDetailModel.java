package com.ralf.module_community.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.mvp.contract.ChannelDetailContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelDetailModel
 * @email -
 * @date 2019/04/24 上午11:11
 **/
@ActivityScope
public class ChannelDetailModel extends BaseModel implements ChannelDetailContract.Model {

    @Inject
    public ChannelDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
