package com.ralf.module_community.mvp.model;

import com.google.gson.JsonObject;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_community.constant.Constant;
import com.ralf.module_community.entity.result.PraiseListResultEntity;
import com.ralf.module_community.http.CommunityService;
import com.ralf.module_community.mvp.contact.PraiseListContract;
import com.ralf.pet_provider.http.BaseEntity;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PraiseListModel
 * @email -
 * @date 2019/04/08 上午8:04
 **/
public class PraiseListModel extends BaseModel implements PraiseListContract.Model {

    @Inject
    public PraiseListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public Observable<BaseEntity<PraiseListResultEntity>> getPraiseList(int userId, int page) {
        JsonObject object = new JsonObject();
        object.addProperty(Constant.DYNAMIC_ID, userId);
        object.addProperty(Constant.PAGE, page);
        return mRepositoryManager.obtainRetrofitService(CommunityService.class)
                .getPraiseList(object);
    }
}
