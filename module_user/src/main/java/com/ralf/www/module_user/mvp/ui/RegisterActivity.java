package com.ralf.www.module_user.mvp.ui;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.www.module_user.R;
import com.ralf.www.module_user.R2;
import com.ralf.www.module_user.dg.component.DaggerRegisterComponent;
import com.ralf.www.module_user.dg.module.RegisterModule;
import com.ralf.www.module_user.mvp.contact.RegisterContact;
import com.ralf.www.module_user.mvp.presenter.RegisterPresenter;
import com.ralf.www.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.www.pet_provider.http.HttpUrl;
import com.ralf.www.pet_provider.router.RouterConfig;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wanglixin
 */
@Route(path = RouterConfig.UserModule.REGISTER_PATH)
public class RegisterActivity extends BaseSwipeBackActivity<RegisterPresenter> implements RegisterContact.View {

    private static final String REGISTER_INFO = "已阅读并同意,牵宠用户协议及牵宠隐私协议";
    private static final String TITLE = "注册";
    private static final String PROTOCOL_USER = "用户协议";
    private static final String PROTOCOL_PET = "隐私协议";

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R2.id.phone_register_et)
    EditText mPhoneEt;
    @BindView(R2.id.password_register_et)
    EditText mPasswordEt;
    @BindView(R2.id.edit_vertify_code_et)
    EditText mCodeEt;
    @BindView(R2.id.send_vertify_code_btn)
    Button mSendCodeBtn;
    @BindView(R2.id.register_new_user_btn)
    Button mRegisterBtn;
    @BindView(R2.id.user_protocol_read_state_cb)
    CheckBox mCheckBox;
    @BindView(R2.id.register_user_protocol_tv)
    TextView mProtocolTv;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerRegisterComponent.builder()
                .appComponent(appComponent)
                .registerModule(new RegisterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_register;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mTitleNameTv.setText(TITLE);
        mSendCodeBtn.setEnabled(false);
        setClickableSpan();
        checkBtnState();
    }

    /**
     * 设置用户协议
     */
    private void setClickableSpan() {
        SpannableString spannableString = new SpannableString(REGISTER_INFO);
        // 用户协议
        spannableString.setSpan(new MyClickableSpan(HttpUrl.LOGIN_USER_PROTOCOL, PROTOCOL_USER), 7, 13,
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        // 宠物协议
        spannableString.setSpan(new MyClickableSpan(HttpUrl.PET_PROTOCOL, PROTOCOL_PET), 14, REGISTER_INFO.length(),
                Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mProtocolTv.setMovementMethod(LinkMovementMethod.getInstance());
        mProtocolTv.setText(spannableString);
        mProtocolTv.setHighlightColor(Color.parseColor("#00ffffff"));
    }

    @Override
    public void checkBtnState() {

        mSendCodeBtn.setText("发送验证码");
        mSendCodeBtn.setEnabled(false);

        // 设置发送验证码按钮状态
        mPresenter.setSendCodeBtnEvent(RxTextView.textChanges(mPhoneEt),
                RxTextView.textChanges(mPasswordEt));

        // 设置注册按钮状态
        mPresenter.setRegisterBtnEvent(RxTextView.textChanges(mPhoneEt),
                RxTextView.textChanges(mPasswordEt),
                RxTextView.textChanges(mCodeEt),
                RxCompoundButton.checkedChanges(mCheckBox));
    }

    @OnClick({R2.id.back_iv, R2.id.send_vertify_code_btn, R2.id.register_new_user_btn})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.back_iv) {
            finish();
        } else if (id == R.id.send_vertify_code_btn) {
            mPresenter.requestVertifyCode();
        } else if (id == R.id.register_new_user_btn) {
            mPresenter.vertifyCode();
        }
    }

    @Override
    public void enableRegisterbBtn(boolean isEnable) {
        mRegisterBtn.setEnabled(isEnable);
    }

    @Override
    public void enableSendCodeBtn(boolean isEnable) {
        mSendCodeBtn.setEnabled(isEnable);
    }

    @Override
    public void updateSendCodeBtn(String msg) {
        mSendCodeBtn.setText(msg);
    }

    @Override
    public void jumpToUserSettingPage(String phone, String passWord, String toastMsg) {
        ARouter.getInstance()
                .build(RouterConfig.UserModule.REGISTER_USER_PATH)
                .withString(RouterConfig.UserModule.KEY_USER_PHONE, phone)
                .withString(RouterConfig.UserModule.KEY_USER_PASSWORD, passWord)
                .navigation();
        ToastUtils.showShort("注册成功，请设置个人信息！");
        finish();
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    private class MyClickableSpan extends ClickableSpan {

        private String mUrl;
        private String mTitle;

        public MyClickableSpan(String url, String title) {
            this.mUrl = url;
            this.mTitle = title;
        }

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
                    .withString(RouterConfig.UserModule.KEY_USER_PROTOCOL_URL, mUrl)
                    .withString(RouterConfig.UserModule.KEY_USER_PROTOCOL_TITLE, mTitle)
                    .navigation();
        }
    }
}
