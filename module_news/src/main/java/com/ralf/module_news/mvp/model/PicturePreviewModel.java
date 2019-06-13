package com.ralf.module_news.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_news.entity.CollectionEntity;
import com.ralf.module_news.entity.NewsDetailEntity;
import com.ralf.module_news.http.NewsService;
import com.ralf.module_news.mvp.contract.PicturePreviewContract;
import com.ralf.pet_provider.constant.PetConstant;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PicturePreviewModel
 * @email -
 * @date 2019/06/01 14:03
 **/
@ActivityScope
public class PicturePreviewModel extends BaseModel implements PicturePreviewContract.Model {

    @Inject
    public PicturePreviewModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<BaseEntity<NewsDetailEntity>> getPhotoData(String newsId) {
        JsonObject object = new JsonObject();
        object.addProperty(PetConstant.ID, newsId);
        return mRepositoryManager.obtainRetrofitService(NewsService.class)
                .getNewsDetailData(object);
    }

    @Override
    public Observable<BaseEntity<CollectionEntity>> requestCollection(int id, int wish) {
        JsonObject object = new JsonObject();
        object.addProperty(PetConstant.RELEVANCEID, id);
        object.addProperty(PetConstant.TYPE, wish);
        return mRepositoryManager.obtainRetrofitService(NewsService.class)
                .requestCollection(object);
    }
}
