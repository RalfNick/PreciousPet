package com.ralf.www.module_user.mvp.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.integration.AppManager;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.StringUtils;
import com.jess.arms.utils.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.ralf.www.module_user.R;
import com.ralf.www.module_user.R2;
import com.ralf.www.module_user.dg.component.DaggerRegisterUserComponent;
import com.ralf.www.module_user.mvp.contact.RegisterUserContact;
import com.ralf.www.module_user.mvp.presenter.RegisterUserPresenter;
import com.ralf.www.pet_provider.router.RouterConfig;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RegisterUserActivity
 * @email -
 * @date 2018/12/11 下午1:21
 **/
@Route(path = RouterConfig.UserModule.REGISTER_USER_PATH)
public class RegisterUserActivity extends BaseActivity<RegisterUserPresenter>
        implements RegisterUserContact.View {

    private static final String TITLE = "主人资料";

    @BindView(R2.id.back_iv)
    ImageView mBackIv;
    @BindView(R2.id.title_name_tv)
    TextView mTitleNameTv;
    @BindView(R2.id.register_user_image)
    ImageView mUserImage;
    @BindView(R2.id.register_user_set_name_et)
    EditText mUserSetNameEt;
    @BindView(R2.id.register_user_sex_rg)
    RadioGroup mRadioGroup;
    @BindView(R2.id.register_new_user_finish_btn)
    Button mRegisterUserBtn;

    @Inject
    RxPermissions mRxPermissions;

    @Autowired(name = RouterConfig.UserModule.KEY_USER_PHONE)
    String mPhone;

    @Autowired(name = RouterConfig.UserModule.KEY_USER_PASSWORD)
    String mPassWord;

    private int mSex = 1;
    private String mNickName;
    private String mImagePath;

    private ImageLoader mImageLoader;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

        DaggerRegisterUserComponent.builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_register_user;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

        mBackIv.setVisibility(View.GONE);
        mTitleNameTv.setText(TITLE);

        mPresenter.requestPermission();
        setViewChangeEvent();
        //可以在任何可以拿到 Context 的地方, 拿到 AppComponent, 从而得到用 Dagger 管理的单例对象
        mImageLoader = ArmsUtils.obtainAppComponentFromContext(this).imageLoader();
    }

    private void setViewChangeEvent() {

        RxTextView.textChanges(mUserSetNameEt).subscribe(charSequence -> {
            if (charSequence.toString().length() > 0) {
                if (!StringUtils.isEmpty(mImagePath) && mImagePath.length() > 0) {
                    mNickName = charSequence.toString();
                    mRegisterUserBtn.setEnabled(true);
                } else {
                    mRegisterUserBtn.setEnabled(false);
                }
            } else {
                mRegisterUserBtn.setEnabled(false);
            }
        });

        RxRadioGroup.checkedChanges(mRadioGroup).subscribe(integer -> {
            if (R.id.register_sex_woman_rb == integer) {
                mSex = 1;
            } else if (R.id.register_sex_man_rb == integer) {
                mSex = 0;
            }
        });

        RxView.clicks(mUserImage).subscribe(o ->
                PictureSelector.create(RegisterUserActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .compress(true)
                        .isCamera(true)
                        .selectionMode(PictureConfig.SINGLE)
                        .imageSpanCount(4)
                        .previewImage(true)
                        .previewVideo(false)
                        .isGif(false)
                        .enableCrop(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList != null && selectList.size() > 0) {
                        mImagePath = selectList.get(0).getCompressPath();
                        ImageConfigImpl config = ImageConfigImpl.builder()
                                .isCenterCrop(true)
                                .isCircle(true)
                                .url(mImagePath)
                                .imageView(mUserImage)
                                .build();
                        mImageLoader.loadImage(this, config);
                        if (!StringUtils.isEmpty(mNickName)) {
                            mRegisterUserBtn.setEnabled(true);
                        }
                    } else {
                        mRegisterUserBtn.setEnabled(false);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @OnClick(R2.id.register_new_user_finish_btn)
    public void onViewClicked() {
        mPresenter.finishRegister(mImagePath, mNickName, mSex, mPhone, mPassWord);
    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }

    @Override
    public void jumpToLoginPage() {
        ARouter.getInstance()
                .build(RouterConfig.UserModule.LOGIN_PATH)
                .navigation();
        AppManager.getAppManager().killActivity(EntranceActivity.class);
        finish();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public RxPermissions getPermissons() {
        return mRxPermissions;
    }
}
