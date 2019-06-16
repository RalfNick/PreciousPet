package com.ralf.module_mall.mvp.model;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.ralf.module_mall.http.MallService;
import com.ralf.module_mall.mvp.contract.MallContract;
import com.ralf.pet_provider.user.constant.UserConstant;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MallModel
 * @email -
 * @date 2019/06/13 19:21
 **/
@FragmentScope
public class MallModel extends BaseModel implements MallContract.Model {

    @Inject
    public MallModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public Observable<ResponseBody> getMallData() {
        return mRepositoryManager.obtainRetrofitService(MallService.class)
                .getMallData(UserConstant.APP_TOKEN);
    }
}
