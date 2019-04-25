package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.result.ChannelResultEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contract.ChannelContract;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

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

    @Override
    public Observable<BaseEntity<ChannelResultEntity>> getChannelData(int currentPage) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.PAGE, currentPage);
        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getChannelData(object);
    }
}
