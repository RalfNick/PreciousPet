package com.ralf.module_news.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_news.entity.NewsResultEntity;
import com.ralf.module_news.http.NewsService;
import com.ralf.module_news.mvp.contract.RecommendContract;
import com.ralf.pet_provider.constant.PetConstant;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendModel
 * @email -
 * @date 2019/05/15 19:34
 **/
@FragmentScope
public class RecommendModel extends BaseModel implements RecommendContract.Model {

    @Inject
    public RecommendModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<BaseEntity<NewsResultEntity>> getRecommendData(int category, int currentPage) {
        JsonObject object = new JsonObject();
        object.addProperty(PetConstant.PAGE, currentPage);
        object.addProperty(PetConstant.CATEGORY, category);
        return mRepositoryManager.obtainRetrofitService(NewsService.class)
                .getRecommendData(object);
    }
}
