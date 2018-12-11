package com.ralf.www.module_user.mvp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jess.arms.di.component.AppComponent;
import com.ralf.www.module_user.R;
import com.ralf.www.module_user.R2;
import com.ralf.www.module_user.dg.component.DaggerPswModifyComponent;
import com.ralf.www.module_user.dg.module.PswModifyModule;
import com.ralf.www.module_user.mvp.contact.PswModifyContact;
import com.ralf.www.module_user.mvp.presenter.PswModifyPresenter;
import com.ralf.www.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.www.pet_provider.router.RouterConfig;
import com.ralf.www.pet_provider.util.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author wanglixin
 */
@Route(path = RouterConfig.UserModule.MODIFY_PASSWORD_PATH)
public class PassWordModifyActivity extends BaseSwipeBackActivity<PswModifyPresenter>
        implements PswModifyContact.View {

    private static final String TITLE = "修改密码";
    private static final String SUCCESS = "0";

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R2.id.phone_modify_et)
    EditText mPhoneModifyEt;
    @BindView(R2.id.password_modify_et)
    EditText mPasswordModifyEt;
    @BindView(R2.id.edit_vertify_code_et)
    EditText mEditVertifyCodeEt;
    @BindView(R2.id.send_vertify_code_btn)
    Button mSendVertifyCodeBtn;
    @BindView(R2.id.modify_password_save_btn)
    Button mSaveBtn;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerPswModifyComponent.builder()
                .appComponent(appComponent)
                .pswModifyModule(new PswModifyModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_pass_word_modify;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mTitleNameTv.setText(TITLE);
        checkEditTexts();
    }

    @Override
    public void checkEditTexts() {
        mSendVertifyCodeBtn.setText("发送验证码");
        mSendVertifyCodeBtn.setEnabled(false);
        mPresenter.setThreeEditTextObserver(RxTextView.textChanges(mPhoneModifyEt),
                RxTextView.textChanges(mPasswordModifyEt),
                RxTextView.textChanges(mEditVertifyCodeEt));

        mPresenter.setTwoEditTextObserver(RxTextView.textChanges(mPhoneModifyEt),
                RxTextView.textChanges(mPasswordModifyEt));
    }


    @OnClick({R2.id.back_iv, R2.id.send_vertify_code_btn, R2.id.modify_password_save_btn})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.back_iv) {
            finish();
        } else if (id == R.id.send_vertify_code_btn) {
            mPresenter.sendVertifyCode();
        } else if (id == R.id.modify_password_save_btn) {
            mPresenter.saveUserPassWord();
        }
    }

    @Override
    public void showMessage(@NonNull String message) {

        if (SUCCESS.equals(message)) {
            ToastUtils.showShort("密码设置成功");
            finish();
        } else {
            ToastUtils.showShort(message);
        }
    }

    @Override
    public void enableSendCodeBtn(boolean result) {
        mSendVertifyCodeBtn.setEnabled(result);
    }

    @Override
    public void enableSaveBtn(boolean result) {
        mSaveBtn.setEnabled(result);
    }

    @Override
    public void updateSendCodeBtn(String msg) {
        mSendVertifyCodeBtn.setText(msg);
    }
}
