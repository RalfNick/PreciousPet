package com.ralf.module_news.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseLazyFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_news.R;
import com.ralf.module_news.dg.component.DaggerNewsImportantComponent;
import com.ralf.module_news.dg.module.NewsImportantModule;
import com.ralf.module_news.mvp.contract.NewsImportantContract;
import com.ralf.module_news.mvp.contract.RecommendContract;
import com.ralf.module_news.mvp.presenter.NewsImportantPresenter;
import com.ralf.module_news.mvp.presenter.RecommendPresenter;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsRecommendFragment
 * @email -
 * @date 2019/05/15 17:58
 **/
public class NewsImportantFragment extends BaseLazyFragment<NewsImportantPresenter> implements NewsImportantContract.View {

    @Override
    protected void loadLargeData() {

    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerNewsImportantComponent.builder()
                .appComponent(appComponent)
                .newsImportantModule(new NewsImportantModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_important, container, false);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }
}
