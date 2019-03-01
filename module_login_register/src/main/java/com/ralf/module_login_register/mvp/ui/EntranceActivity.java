package com.ralf.module_login_register.mvp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.AppManager;
import com.ralf.module_login_register.R;
import com.ralf.module_login_register.R2;
import com.ralf.pet_provider.chat.ChatUtil;
import com.ralf.pet_provider.router.RouterConfig;
import com.ralf.pet_provider.user.UserUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wanglixin
 */
@Route(path = RouterConfig.LoginRegisterModule.ENTRANCE_PATH)
public class EntranceActivity extends BaseActivity {

    private static final String TAG = EntranceActivity.class.getSimpleName();

    @BindView(R2.id.activity_entrance_register)
    Button mRegisterBtn;
    @BindView(R2.id.activity_entrance_login)
    Button mLoginBtn;

    @Autowired(name = RouterConfig.LoginRegisterModule.KEY_LOGOUT)
    String flag;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_entrance;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setTranslucent(this, 1);
        // 账号在其他地方登陆
        if (RouterConfig.LoginRegisterModule.VALUE_LOGOUT.equals(flag)) {
            flag = "";
            AppManager.getAppManager().killAll(EntranceActivity.class);
            UserUtil.removeToken();
            // 退出环信
            ChatUtil.logout();
        }
    }

    @OnClick({R2.id.activity_entrance_register, R2.id.activity_entrance_login})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.activity_entrance_register) {
            ARouter.getInstance().build(RouterConfig.LoginRegisterModule.REGISTER_PATH).navigation();
        } else if (i == R.id.activity_entrance_login) {
            ARouter.getInstance()
                    .build(RouterConfig.LoginRegisterModule.LOGIN_PATH)
                    .navigation();
        }
    }
}
