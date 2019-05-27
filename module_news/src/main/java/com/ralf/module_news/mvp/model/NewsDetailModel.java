package com.ralf.module_news.mvp.model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_news.mvp.contract.NewsDetailContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsDetailModel
 * @email -
 * @date 2019/05/27 19:32
 **/
@ActivityScope
public class NewsDetailModel extends BaseModel implements NewsDetailContract.Model{

    @Inject
    public NewsDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
