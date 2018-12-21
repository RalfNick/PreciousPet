package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.entity.FeaturedEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contact.FeaturedContact;
import com.ralf.pet_provider.http.BaseEntity;
import com.ralf.pet_provider.user.constant.CommunityConstant;

import javax.inject.Inject;

import io.reactivex.Observable;

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

    @Override
    public Observable<BaseEntity<FeaturedEntity>> getFeaturedData(int page, int type) {

        JsonObject object = new JsonObject();
        object.addProperty(CommunityConstant.DYNAMIC_TYPE,type);
        object.addProperty(CommunityConstant.PAGE,page);

        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getFeaturedData(object);
    }
}
