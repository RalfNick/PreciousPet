package com.ralf.preciouspet;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.EaseConstant;
import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.event.transmit.EventPublicApi;
import com.jess.arms.event.transmit.EventPublicApiHelper;
import com.jess.arms.utils.PermissionUtils;
import com.jess.arms.utils.SpUtil;
import com.jess.arms.utils.ToastUtils;
import com.jess.arms.utils.constant.PermissionConstants;
import com.orhanobut.logger.Logger;
import com.ralf.module_chat.ChatHelper;
import com.ralf.module_db.data.entity.PetAssistantEntity;
import com.ralf.module_db.util.GreenDaoUtils;
import com.ralf.pet_provider.base.SimpleObserver;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.share.PetShare;
import com.ralf.pet_provider.user.constant.UserConstant;
import com.ralf.pet_provider.util.NetUtil;
import com.ralf.pet_provider.widget.dialog.DialogSure;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wanglixin
 */
@Route(path = RouterConfig.AppModule.MAIN_PATH)
public class PetMainActivity extends BaseActivity implements ChatHelper.PushMsgInterface, EventPublicApi.LogoutApi {

    private static final int PERMISSION_CODE = 120;

    @BindView(R.id.main_fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.main_view_switch_rg)
    RadioGroup mViewSwitchRg;
    @BindView(R.id.main_my_msg_unread)
    TextView mMsgCount;
    @BindView(R.id.main_network_unavailable_ll)
    LinearLayout mNetworkLl;
    @BindView(R.id.main_reload_btn)
    Button mReloadBtn;

    /**
     * 当前选择的 Fragment 的路径
     */
    private String mFragmentPath;
    private Set<String> mFragmentTagSet = new HashSet<>();

    private FragmentManager mFragmentManager;

    /**
     * 记录退出时间
     */
    private long mExitTime;

    /**
     * 退出登录对话框
     */
    private DialogSure mDialogSure;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_pet_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        // 首先显示社区
        mFragmentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
        mFragmentManager = getSupportFragmentManager();
        checkNetWork();
        initRaidoCheckedEvent();
        loadUserInfo();
        ChatHelper.getInstance().setPushMsg(this);
        getPermission();
    }

    /**
     * 动态请求权限
     */
    private void getPermission() {
        PermissionUtils.permission(PermissionConstants.STORAGE).request(this);
        if (Build.VERSION.SDK_INT >= PERMISSION_CODE) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_LOGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.SET_DEBUG_APP,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,
                    Manifest.permission.GET_ACCOUNTS,
                    Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    /**
     * 加载用户信息
     */
    private void loadUserInfo() {

        UserConstant.APP_TOKEN = SpUtil.getInstance().getString(UserConstant.USER_TOKEN);
        UserConstant.APP_USERID = SpUtil.getInstance().getInt(UserConstant.USER_ID);
        UserConstant.APP_NICKNAME = SpUtil.getInstance().getString(UserConstant.USER_NAME);
        UserConstant.APP_IMAGE = SpUtil.getInstance().getString(UserConstant.USER_IMAGE);
        UserConstant.APP_HX_USERID = SpUtil.getInstance().getString(UserConstant.HX_USER_NAME);
        UserConstant.APP_SEX = SpUtil.getInstance().getInt(UserConstant.USER_SEX);

        EaseConstant.TO_HX_USERID = UserConstant.APP_HX_USERID;

        // 加载助手信息
        List<PetAssistantEntity> entities = GreenDaoUtils.getInstance(getApplication())
                .queryAll(PetAssistantEntity.class);
        if (entities != null && entities.size() > 0) {
            UserConstant.ASSISTANT_ID = entities.get(0).getQCuserId();
            UserConstant.ASSISTANT_HXID = entities.get(0).getQChxId();
            UserConstant.ASSISTANT_NAME = entities.get(0).getQCnickName();
            UserConstant.ASSISTANT_IMAGE = entities.get(0).getQCheadPortrait();
        }
    }

    /**
     * 检查网络
     */
    private void checkNetWork() {
        if (!NetUtil.isNetworkConnected(this)) {
            mNetworkLl.setVisibility(View.VISIBLE);
        } else {
            mNetworkLl.setVisibility(View.GONE);
        }
    }

    /**
     * 跳转到不同的 Fragment
     */
    private void initRaidoCheckedEvent() {

        RxRadioGroup.checkedChanges(mViewSwitchRg)
                .subscribe(new SimpleObserver<Integer>() {
                    @Override
                    protected void onSuccess(Integer data) {
                        switch (data) {
                            case R.id.main_community_rb:
                                mFragmentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
                                break;
                            case R.id.main_information_rb:
//                                mFragmentPath = RouterConfig.NewsModule.NEWS_PATH;
                                mFragmentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
                                break;
                            case R.id.main_service_rb:
//                                mFragmentPath = RouterConfig.ServiceModule.SERVICE_PATH;
                                mFragmentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
                                break;
                            case R.id.main_shopping_rb:
//                                mFragmentPath = RouterConfig.MallModule.MALL_PATH;
                                mFragmentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
                                break;

                            default:
                                mFragmentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
                                break;
                        }
                        switchFragment();
                    }

                    @Override
                    protected void onFailed() {
                        Logger.e("跳转到fragment失败");
                    }
                });
    }

    /**
     * 切换到不同的 Fragment
     */
    private void switchFragment() {

        mFragmentTagSet.add(mFragmentPath);
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideAllFragments(transaction);
        Fragment fragment = mFragmentManager.findFragmentByTag(mFragmentPath);
        if (fragment == null) {
            fragment = (Fragment) ARouter.getInstance().build(mFragmentPath).navigation();
            transaction.add(R.id.main_fragment_container, fragment, mFragmentPath);
        }
        transaction.show(fragment);
        transaction.commit();
    }

    /**
     * 隐藏所有的 Fragment
     *
     * @param transaction
     */
    private void hideAllFragments(FragmentTransaction transaction) {

        if (mFragmentTagSet.isEmpty()) {
            return;
        }
        for (String tag : mFragmentTagSet) {
            Fragment fragment = mFragmentManager.findFragmentByTag(tag);
            if (fragment != null) {
                transaction.hide(fragment);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventPublicApiHelper.register(EventPublicApi.LogoutApi.class, this);
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - mExitTime > 2000) {
            mExitTime = System.currentTimeMillis();
            ToastUtils.showShort("再按一次退出程序");
        } else {
            finish();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PetShare.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PetShare.release(this);
        EventPublicApiHelper.unregister(EventPublicApi.LogoutApi.class);
    }

    @OnClick(R.id.main_reload_btn)
    public void onViewClicked() {
        switchFragment();
    }

    @Override
    public void getPushMsg() {
        ToastUtils.showShort("推送消息");
        int count = EMClient.getInstance().chatManager().getUnreadMessageCount();
        if (count > 0) {
            mMsgCount.setText(String.valueOf(count));
            mMsgCount.setVisibility(View.VISIBLE);
        } else {
            mMsgCount.setVisibility(View.GONE);
        }
    }

    @Override
    public void getMyPushMsg() {

    }

    @Override
    public void jumpToLoginPage() {
        if (mDialogSure == null) {
            mDialogSure = new DialogSure.Builder(this)
                    .content("您的账号已在别处登录，请重新登录")
                    .cancelable(false)
                    .title("下线通知")
                    .sureListener(dialog -> {
                        ARouter.getInstance().build(RouterConfig.LoginRegisterModule.ENTRANCE_PATH).navigation();
                        dialog.dismiss();
                    })
                    .build();
        }
        if (!mDialogSure.isShowing()) {
            mDialogSure.show();
        }
    }
}
