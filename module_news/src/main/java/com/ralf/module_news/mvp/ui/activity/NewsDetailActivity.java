package com.ralf.module_news.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_news.R;
import com.ralf.module_news.dg.component.DaggerNewsDetailComponent;
import com.ralf.module_news.dg.module.NewsDetailModule;
import com.ralf.module_news.mvp.contract.NewsDetailContract;
import com.ralf.module_news.mvp.presenter.NewsDetailPresenter;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsDetailActivity
 * @email -
 * @date 2019/05/27 19:30
 **/
@Route(path = RouterConfig.NewsModule.NEWS_PATH_DETAIL)
public class NewsDetailActivity extends BaseSwipeBackActivity<NewsDetailPresenter>
        implements NewsDetailContract.View {

    @Autowired(name = RouterConfig.NewsModule.KEY_NEWS_ID)
    String mNewsId;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        DaggerNewsDetailComponent.builder()
                .appComponent(appComponent)
                .newsDetailModule(new NewsDetailModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_news_detail;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }
}
