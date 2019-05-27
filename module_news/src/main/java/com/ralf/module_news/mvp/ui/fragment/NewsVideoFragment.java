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
import com.ralf.module_news.dg.component.DaggerNewsVideoComponent;
import com.ralf.module_news.dg.module.NewsVideoModule;
import com.ralf.module_news.mvp.contract.NewsVideoContract;
import com.ralf.module_news.mvp.presenter.NewsVideoPresenter;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsRecommendFragment
 * @email -
 * @date 2019/05/15 17:58
 **/
public class NewsVideoFragment extends BaseLazyFragment<NewsVideoPresenter> implements NewsVideoContract.View {

    @Override
    protected void loadLargeData() {

    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerNewsVideoComponent.builder()
                .appComponent(appComponent)
                .newsVideoModule(new NewsVideoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_video, container, false);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }
}
