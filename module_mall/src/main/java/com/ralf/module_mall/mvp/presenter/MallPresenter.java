package com.ralf.module_mall.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RxLifecycleUtils;
import com.orhanobut.logger.Logger;
import com.ralf.module_mall.constant.KeyConstant;
import com.ralf.module_mall.mvp.contract.MallContract;
import com.ralf.pet_provider.base.SimpleObserver;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import okhttp3.ResponseBody;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name MallPresenter
 * @email -
 * @date 2019/06/13 19:22
 **/
@FragmentScope
public class MallPresenter extends BasePresenter<MallContract.Model, MallContract.View> {

    @Inject
    RxErrorHandler mHandler;

    @Inject
    public MallPresenter(MallContract.Model model, MallContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void addDispose(Disposable disposable) {
        super.addDispose(disposable);
    }

    /**
     * 请求购物数据
     *
     * @param isRefresh 是否是刷新
     */
    public void getMallData(boolean isRefresh) {
        mModel.getMallData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new SimpleObserver<ResponseBody>() {

                    @Override
                    protected void onSuccess(ResponseBody data) {
                        try {
                            JSONObject object = new JSONObject(data.string());
                            Logger.e("ShoppingObserver**N", object.toString());
                            mRootView.showDataOnView(isRefresh, object.getJSONObject(KeyConstant.KEY_DATA).getJSONArray(KeyConstant.KEY_CARDS));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    protected void onFailed() {
                        mRootView.hideLoading();
                        mRootView.showMessage("网络请求失败");
                    }
                });
    }
}
