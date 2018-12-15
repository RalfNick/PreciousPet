package com.ralf.module_login_register.mvp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jaeger.library.StatusBarUtil;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.ralf.module_login_register.R;
import com.ralf.module_login_register.R2;
import com.ralf.pet_provider.router.RouterConfig;

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
    }

    @OnClick({R2.id.activity_entrance_register, R2.id.activity_entrance_login})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.activity_entrance_register) {
            ARouter.getInstance().build(RouterConfig.LoginRegisterModule.REGISTER_PATH).navigation();
        } else if (i == R.id.activity_entrance_login) {
            ARouter.getInstance().build(RouterConfig.LoginRegisterModule.LOGIN_PATH).navigation();
        }
    }
}
