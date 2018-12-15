package com.ralf.preciouspet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.orhanobut.logger.Logger;
import com.ralf.pet_provider.base.SimpleObserver;
import com.ralf.pet_provider.router.RouterConfig;

import butterknife.BindView;

/**
 * @author wanglixin
 */
@Route(path = RouterConfig.AppModule.MAIN_PATH)
public class PetMainActivity extends BaseActivity {

    @BindView(R.id.main_fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.main_view_switch_rg)
    RadioGroup mViewSwitchRg;

    /**
     * 当前选择的 Fragment 的路径
     */
    private String mFramentPath;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_pet_main;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        checkNetWork();
        initRaidoCheckedEvent();
    }

    /**
     * 检查网络
     */
    private void checkNetWork() {

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
                                mFramentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
                                break;
                            case R.id.main_information_rb:
                                mFramentPath = RouterConfig.NewsModule.NEWS_PATH;
                                break;
                            case R.id.main_service_rb:
                                mFramentPath = RouterConfig.ServiceModule.SERVICE_PATH;
                                break;
                            case R.id.main_shopping_rb:
                                mFramentPath = RouterConfig.MallModule.MALL_PATH;
                                break;

                            default:
                                mFramentPath = RouterConfig.CommunityModule.COMMUNITY_PATH;
                                break;
                        }
                    }

                    @Override
                    protected void onFailed() {

                        Logger.e("跳转到fragment失败");
                    }
                });
    }
}
