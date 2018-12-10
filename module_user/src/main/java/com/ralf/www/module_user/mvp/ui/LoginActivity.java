package com.ralf.www.module_user.mvp.ui;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
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
import com.jess.arms.di.component.AppComponent;
import com.ralf.www.module_user.R;
import com.ralf.www.module_user.R2;
import com.ralf.www.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.www.pet_provider.http.HttpUrl;
import com.ralf.www.pet_provider.router.RouterConfig;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wanglixin
 */
@Route(path = RouterConfig.UserModule.LOGIN_PATH)
public class LoginActivity extends BaseSwipeBackActivity {

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

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mTitleNameTv.setText("登录");
        // 背景色
        Drawable bgDrawable = getResources().getDrawable(R.mipmap.bg_title_bar, null);
        mLayoutTitle.setBackground(bgDrawable);
        setClickableSpan();
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

    @OnClick({R2.id.back_iv, R2.id.login_phone_edit, R2.id.login_password_edit, R2.id.login_forgot_password_btn})
    public void onViewClicked(View view) {

        int i = view.getId();

        if (i == R.id.back_iv) {
            finish();
        } else if (i == R.id.login_phone_edit) {

        } else if (i == R.id.login_password_edit) {

        } else if (i == R.id.login_forgot_password_btn) {

        }
    }

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
                    .build(RouterConfig.UserModule.USER_PROTOCOL_PATH)
                    .withString(RouterConfig.UserModule.KEY_USER_PROTOCOL_URL, HttpUrl.LOGIN_USER_PROTOCOL)
                    .navigation();
        }
    }
}
