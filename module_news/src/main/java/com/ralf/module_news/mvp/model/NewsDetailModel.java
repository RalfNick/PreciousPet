package com.ralf.module_news.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_news.entity.NewsDetailEntity;
import com.ralf.module_news.http.NewsService;
import com.ralf.module_news.mvp.contract.NewsDetailContract;
import com.ralf.pet_provider.constant.PetConstant;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsDetailModel
 * @email -
 * @date 2019/05/27 19:32
 **/
@ActivityScope
public class NewsDetailModel extends BaseModel implements NewsDetailContract.Model {

    @Inject
    public NewsDetailModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<BaseEntity<NewsDetailEntity>> getNewsDetailData(String newsId) {
        JsonObject object = new JsonObject();
        object.addProperty(PetConstant.ID, newsId);
        return mRepositoryManager.obtainRetrofitService(NewsService.class)
                .getNewsDetailData(object);
    }
}
