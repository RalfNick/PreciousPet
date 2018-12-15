package com.ralf.www.module_user.mvp.presenter;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.AppManager;
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.utils.RegexUtils;
import com.jess.arms.utils.RxLifecycleUtils;
import com.jess.arms.utils.SpUtil;
import com.orhanobut.logger.Logger;
import com.ralf.module_db.data.entity.MessageRemindEntity;
import com.ralf.module_db.data.entity.PetEntity;
import com.ralf.module_db.util.GreenDaoUtils;
import com.ralf.www.module_user.constant.LoginEnum;
import com.ralf.www.module_user.entity.LoginEntity;
import com.ralf.www.module_user.entity.ThirdPartLoginEntity;
import com.ralf.www.module_user.mvp.contact.LoginContact;
import com.ralf.www.pet_provider.base.SimpleObserver;
import com.ralf.www.pet_provider.chat.ChatCallBack;
import com.ralf.www.pet_provider.chat.ChatUtil;
import com.ralf.www.pet_provider.constant.PetConstant;
import com.ralf.www.pet_provider.http.BaseEntity;
import com.ralf.www.pet_provider.http.WebObserver;
import com.ralf.www.pet_provider.user.constant.UserConstant;
import com.ralf.www.pet_provider.util.BitmapUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name LoginPresenter
 * @email -
 * @date 2018/12/13 上午10:54
 **/

@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContact.Model, LoginContact.View> {

    @Inject
    RxErrorHandler mErrorHandler;

    private String mPhone;
    private String mPassWord;
    private JsonObject mObject = new JsonObject();

    /**
     * 友盟回调
     */
    private UMAuthListener mUMAuthListener;
    private Application mApplication;

    @Inject
    public LoginPresenter(LoginContact.Model model, LoginContact.View rootView) {
        super(model, rootView);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    protected void onCreate() {
        initUmListener();
        mObject.addProperty(PetConstant.PHONE_MODEL, PetConstant.ANDROID_MODEL);
        mObject.addProperty(PetConstant.PHONE_VERSION, PetConstant.ANDROID_VERSION);
        mApplication = AppManager.getAppManager().getApplication();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
    }

    /**
     * 判断输入框，更新登录按钮状态
     *
     * @param phoneObservable phone
     * @param pswObservable   password
     */
    public void setEditTextEvent(Observable<CharSequence> phoneObservable, Observable<CharSequence> pswObservable) {
        Observable.combineLatest(phoneObservable, pswObservable, (phone, password) -> {
            mPhone = phone.toString();
            mPassWord = password.toString();
            return RegexUtils.isMobileExact(mPhone) && mPassWord.length() > 5;
        })

                .skip(1)
                .distinctUntilChanged().subscribe(new SimpleObserver<Boolean>() {
            @Override
            protected void onSuccess(Boolean result) {
                mRootView.updateLoginBtnState(result);
            }

            @Override
            protected void onFailed() {
                mRootView.updateLoginBtnState(false);
            }
        });
    }

    /**
     * 登录
     *
     * @param type
     */
    public void loginRequest(LoginEnum type) {
        switch (type) {
            case TYPE_PHONE:
                loginWithPhone();
                break;
            case TYPE_QQ:
                UMShareAPI.get(mRootView.getActivity())
                        .getPlatformInfo(mRootView.getActivity(), SHARE_MEDIA.QQ, mUMAuthListener);
                break;
            case TYPE_WX:
                UMShareAPI.get(mRootView.getActivity())
                        .getPlatformInfo(mRootView.getActivity(), SHARE_MEDIA.WEIXIN, mUMAuthListener);
                break;
            default:
                break;
        }

    }

    /**
     * 手机登录
     */
    private void loginWithPhone() {
        mModel.loginRequest(mPhone, mPassWord, mObject)
                .subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<LoginEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(LoginEntity loginEntity) {
                        // 登录环信
                        loginHx(loginEntity.getHxUserName());
                        saveUserAndPetInfo(loginEntity);
                        mRootView.jumpToMainPage();
                    }

                    @Override
                    protected void onFailed(String failedMsg) {
                        mRootView.showMessage("登录失败，请重试！");
                    }
                });
    }

    /**
     * 初始化友盟回调
     */
    private void initUmListener() {
        mUMAuthListener = new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA shareMedia) {
                Logger.e("SHARE_MEDIA %s", shareMedia.toString());
            }

            @Override
            public void onComplete(SHARE_MEDIA shareMedia, int i, Map<String, String> map) {
                loginThroughThirdPart(shareMedia, map);
            }

            @Override
            public void onError(SHARE_MEDIA shareMedia, int i, Throwable throwable) {
                Logger.e("onError --- SHARE_MEDIA %s, throwable %s",
                        shareMedia.toString(), throwable.getMessage());
            }

            @Override
            public void onCancel(SHARE_MEDIA shareMedia, int i) {
                Logger.e("SHARE_MEDIA %s", shareMedia.toString());
            }
        };
    }

    /**
     * 友盟返回成功后登录本地服务器
     *
     * @param shareMedia 类型
     * @param map        参数map
     */
    private void loginThroughThirdPart(SHARE_MEDIA shareMedia, Map<String, String> map) {

        int loginType;
        String accessToken = map.get("accessToken");
        String umUid;
        String nickName = map.get("name");
        String iconUrl = map.get("iconurl");
        String sexStr = map.get("gender");
        String openId = map.get("openid");
        int sex = 1;
        if (sexStr.equals("男")) {
            sex = 0;
        }

        switch (shareMedia) {
            case WEIXIN:
                loginType = 1;
                umUid = map.get("openid");
                executeLoginAct(loginType, accessToken, umUid, nickName, iconUrl, openId, sex);
                break;

            case QQ:
                loginType = 4;
                umUid = map.get("uid");
                executeLoginAct(loginType, accessToken, umUid, nickName, iconUrl, openId, sex);
                break;
            default:
                break;
        }
    }

    /**
     * 执行三方登录
     *
     * @param loginType   登录类型
     * @param accessToken token
     * @param nickName    昵称
     * @param iconUrl     头像url
     * @param openId      openId
     * @param sex         性别
     */
    private void executeLoginAct(int loginType, String accessToken, String umUid, String nickName,
                                 String iconUrl, String openId, int sex) {
        Observable.just(iconUrl)
                .subscribeOn(Schedulers.io())
                .flatMap((Function<String, ObservableSource<String>>) picUrl -> {
                    Bitmap bitmap = BitmapUtil.getBitmapFromUrl(picUrl);
                    String base64 = BitmapUtil.transBitmapToBase64(bitmap);
                    return Observable.just(base64);
                })
                .flatMap((Function<String, ObservableSource<BaseEntity<LoginEntity>>>) bitmapStr -> {
                    ThirdPartLoginEntity entity = new ThirdPartLoginEntity(accessToken, loginType, openId);
                    JsonElement jsonElement = new Gson().toJsonTree(entity);
                    return mModel.loginThirdPart(umUid, nickName, sex, bitmapStr, jsonElement, mObject);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new WebObserver<LoginEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(LoginEntity data) {

                        saveUserAndPetInfo(data);
                        initNotificationRemind();
                        mRootView.jumpToMainPage();
                        // 登录环信
                        loginHx(data.getHxUserName());
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage("登录失败，请重试！");
                    }
                });
    }

    /**
     * 将信息保存到 sp,宠物信息保存到数据库
     *
     * @param data 数据
     */
    private void saveUserAndPetInfo(LoginEntity data) {

        // 保存用户信息到 sp
        SpUtil.getInstance().put(UserConstant.USER_TOKEN, data.getToken());
        SpUtil.getInstance().put(UserConstant.USER_ID, data.getUserId());
        SpUtil.getInstance().put(UserConstant.USER_NAME, data.getNickName());
        SpUtil.getInstance().put(UserConstant.USER_IMAGE, data.getUserHeadPortrait());
        SpUtil.getInstance().put(UserConstant.HX_USER_NAME, data.getHxUserName());
        SpUtil.getInstance().put(UserConstant.USER_SEX, data.getUserSex());
        SpUtil.getInstance().put(UserConstant.PLAY_VIDEO_STATUS, 0);

        // 内存缓存
        UserConstant.APP_IMAGE = data.getUserHeadPortrait();
        UserConstant.APP_NICKNAME = data.getNickName();
        UserConstant.APP_TOKEN = data.getToken();
        UserConstant.APP_USERID = data.getUserId();
        UserConstant.APP_SEX = data.getUserSex();

        // 保存宠物信息到数据库
        List<LoginEntity.PetListBean> petList = data.getPetList();
        List<PetEntity> petEntities = new ArrayList<>();

        if (petList != null) {
            for (LoginEntity.PetListBean bean : petList) {
                PetEntity entity = new PetEntity();
                entity.setPetId(bean.getPetId());
                entity.setHeadPortrait(bean.getHeadPortrait());
                entity.setPetName(bean.getPetName());
                entity.setPetSelect(bean.getPetSelect());
                entity.setSex(bean.getSex());
                petEntities.add(entity);
            }
        }
        GreenDaoUtils.getInstance(mApplication).insertTx(petEntities);
    }

    /**
     * 初始化提醒实体
     */
    private void initNotificationRemind() {

        GreenDaoUtils.getInstance(mApplication).deleteList(MessageRemindEntity.class);

        MessageRemindEntity instance = new MessageRemindEntity();
        instance.setComment(0);
        instance.setInformationPush(0);
        instance.setNotification(0);
        instance.setNotifyDisplayMessages(0);
        instance.setPreventTrouble(1);
        instance.setPraise(0);
        instance.setPrivateChat(0);
        instance.setShake(0);
        instance.setSound(0);
        instance.setSystemNotifications(0);
        GreenDaoUtils.getInstance(mApplication).insertOrReplace(instance);
    }

    /**
     * 退出登录
     */
    public void logout() {
        mModel.logoutRequest()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
                .subscribe(new WebObserver<LoginEntity>(mErrorHandler) {
                    @Override
                    protected void onSuccess(LoginEntity data) {
                        // 清除提醒
                        GreenDaoUtils.getInstance(mApplication).deleteAll();
                        // 清除 SP
                        SpUtil.getInstance().clear(true);
                        // 退出环信
                        ChatUtil.logout();
                    }

                    @Override
                    protected void onFailed(String failMsg) {
                        mRootView.showMessage(failMsg);
                    }
                });
    }

    /**
     * 登录环信
     *
     * @param hxUserName 用户名
     */
    private void loginHx(String hxUserName) {

        ChatUtil.login(hxUserName, hxUserName, new ChatCallBack() {
            @Override
            public void onSuccess() {
                Logger.e("登录环信成功");
                mRootView.jumpToMainPage();
            }

            @Override
            public void onFailed(String failMsg) {
                Logger.e("登录环信失败");
            }
        });
    }
}
