package com.ralf.www.preciouspet;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.ralf.www.pet_provider.router.RouterConfig;
import com.ralf.www.pet_provider.user.UserUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name SplashActivity
 * @email -
 * @date 2018/12/05 上午11:37
 **/
public class SplashActivity extends BaseActivity {

    private static final int TOTAL_TIME_SECOND = 3;
    private String mTimeStr = "%s s";

    @BindView(R.id.splash_bg_iv)
    ImageView splashBgIv;
    @BindView(R.id.splash_jump_over_btn)
    Button mSplashJumpOverBtn;

    private Disposable mDisposable;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initBackgroundFullScreen();
        startCount();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    private void startCount() {
        mDisposable = Flowable.intervalRange(0, TOTAL_TIME_SECOND, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(aLong -> {
                    String text = String.format(mTimeStr, TOTAL_TIME_SECOND - aLong);
                    mSplashJumpOverBtn.setText(text);
                })
                .doOnComplete(() -> {
                    if (UserUtil.isUserLogin()) {
                        ARouter.getInstance().build(RouterConfig.AppModule.MAIN_PATH).navigation();
                    } else {
                        ARouter.getInstance().build(RouterConfig.AppModule.ENTRANCE_PATH).navigation();
                    }
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    finish();
                })
                .subscribe();
    }

    private void initBackgroundFullScreen() {

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @OnClick(R.id.splash_jump_over_btn)
    public void onViewClicked() {
    }
}
