package com.ralf.module_news.mvp.model;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_news.mvp.contract.NewsVideoContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendModel
 * @email -
 * @date 2019/05/15 19:34
 **/
@FragmentScope
public class NewsVideoModel extends BaseModel implements NewsVideoContract.Model {

    @Inject
    public NewsVideoModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
