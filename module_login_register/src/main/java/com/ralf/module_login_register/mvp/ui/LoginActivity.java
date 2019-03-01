package com.ralf.module_login_register.mvp.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_login_register.R;
import com.ralf.module_login_register.R2;
import com.ralf.module_login_register.constant.LoginEnum;
import com.ralf.module_login_register.dg.component.DaggerLoginComponent;
import com.ralf.module_login_register.dg.module.LoginModule;
import com.ralf.module_login_register.mvp.contact.LoginContact;
import com.ralf.module_login_register.mvp.presenter.LoginPresenter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.http.HttpUrl;
import com.ralf.pet_provider.router.RouterConfig;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wanglixin
 */
@Route(path = RouterConfig.LoginRegisterModule.LOGIN_PATH)
public class LoginActivity extends BaseSwipeBackActivity<LoginPresenter> implements LoginContact.View {

    private static final String LOGIN_INFO = "已阅读并同意,牵宠用户协议";

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R2.id.login_phone_edit)
    EditText mLoginPhoneEdit;
    @BindView(R2.id.login_password_edit)
    EditText mLoginPasswordEdit;
    @BindView(R2.id.login_forgot_password_btn)
    Button mLoginForgotPasswordBtn;
    @BindView(R2.id.login_btn)
    Button mLoginBtn;
    @BindView(R2.id.login_info_tv)
    TextView mLoginInfoTv;
    @BindView(R2.id.login_weixin_btn)
    ImageView mLoginWeixinBtn;
    @BindView(R2.id.login_qq_btn)
    ImageView mLoginQqBtn;
    @BindView(R2.id.layout_title)
    RelativeLayout mLayoutTitle;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mTitleNameTv.setText("登录");
        setClickableSpan();
        mPresenter.setEditTextEvent(RxTextView.textChanges(mLoginPhoneEdit),
                RxTextView.textChanges(mLoginPasswordEdit));
    }

    /**
     * 设置用户协议
     */
    private void setClickableSpan() {
        SpannableString spannableString = new SpannableString(LOGIN_INFO);
        MyClickableSpan clickableSpan = new MyClickableSpan();
        spannableString.setSpan(clickableSpan, 7, spannableString.length(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mLoginInfoTv.setMovementMethod(LinkMovementMethod.getInstance());
        mLoginInfoTv.setText(spannableString);
        mLoginInfoTv.setHighlightColor(Color.parseColor("#00ffffff"));
    }

    @OnClick({R2.id.back_iv, R2.id.login_btn, R2.id.login_forgot_password_btn})
    public void onViewClicked(View view) {

        int id = view.getId();

        if (id == R.id.back_iv) {
            finish();
        } else if (id == R.id.login_forgot_password_btn) {
            ARouter.getInstance()
                    .build(RouterConfig.LoginRegisterModule.MODIFY_PASSWORD_PATH)
                    .withString(RouterConfig.LoginRegisterModule.KEY_USER_PROTOCOL_URL, HttpUrl.LOGIN_USER_PROTOCOL)
                    .navigation();
        } else if (id == R.id.login_btn) {
            mPresenter.loginRequest(LoginEnum.TYPE_PHONE);
        } else if (id == R.id.login_weixin_btn) {
            mPresenter.loginRequest(LoginEnum.TYPE_WX);
        } else if (id == R.id.login_qq_btn) {
            mPresenter.loginRequest(LoginEnum.TYPE_QQ);
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void updateLoginBtnState(boolean isEnable) {
        mLoginBtn.setEnabled(isEnable);
    }

    @Override
    public void jumpToMainPage() {
        ARouter.getInstance().build(RouterConfig.AppModule.MAIN_PATH).navigation();
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 设置 SpannerString
     */
    private class MyClickableSpan extends ClickableSpan {

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setColor(Color.parseColor("#52AEE5"));
            //设置是否显示下划线
            ds.setUnderlineText(false);
            ds.clearShadowLayer();
        }

        @Override
        public void onClick(View widget) {
            ARouter.getInstance()
                    .build(RouterConfig.LoginRegisterModule.USER_PROTOCOL_PATH)
                    .withString(RouterConfig.LoginRegisterModule.KEY_USER_PROTOCOL_URL, HttpUrl.LOGIN_USER_PROTOCOL)
                    .withString(RouterConfig.LoginRegisterModule.KEY_USER_PROTOCOL_TITLE, "用户协议")
                    .navigation();
        }
    }
}
