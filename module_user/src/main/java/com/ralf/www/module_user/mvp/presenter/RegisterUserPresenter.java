package com.ralf.www.module_user.mvp.presenter;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.PermissionUtil;
import com.ralf.www.module_user.entity.VertifyCodeEntity;
import com.ralf.www.module_user.mvp.contact.RegisterContact;
import com.ralf.www.module_user.mvp.contact.RegisterUserContact;
import com.ralf.www.pet_provider.http.BaseObservser;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RegisterUserPresenter
 * @email -
 * @date 2018/12/12 上午9:52
 **/
@ActivityScope
public class RegisterUserPresenter extends BasePresenter<RegisterContact.Model, RegisterUserContact.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    public RegisterUserPresenter(RegisterContact.Model model, RegisterUserContact.View view) {
        super(model, view);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

    public void requestPermission() {
        //请求外部存储权限用于适配android6.0的权限管理机制
        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {

            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
                mRootView.showMessage("Request permissions failure");
            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
                mRootView.showMessage("Need to go to the settings");
            }
        }, mRootView.getPermissons(), mErrorHandler);
    }

    /**
     * 完成功能
     *
     * @param imagePath 图片路径
     * @param nickName  昵称
     * @param sex       性别
     * @param phone     电话
     * @param passWord  密码
     */
    public void finishRegister(String imagePath, String nickName, int sex, String phone, String passWord) {
        mModel.registerNewUser(imagePath, nickName, phone, passWord, sex)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObservser<VertifyCodeEntity>() {
                    @Override
                    protected void onSuccess(VertifyCodeEntity vertifyCodeEntity) {
                        if (vertifyCodeEntity.getCode() == 0) {
                            mRootView.jumpToLoginPage();
                            mRootView.showMessage("注册成功！");
                        } else {
                            mRootView.showMessage("该用户已注册！");
                        }
                    }

                    @Override
                    protected void onFailed(VertifyCodeEntity vertifyCodeEntity) {
                        mRootView.showMessage("注册失败！");
                    }
                });
    }
}
