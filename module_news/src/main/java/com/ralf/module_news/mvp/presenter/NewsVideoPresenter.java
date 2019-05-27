package com.ralf.module_news.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_news.mvp.contract.NewsContract;
import com.ralf.module_news.mvp.contract.NewsVideoContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendPresenter
 * @email -
 * @date 2019/05/15 19:35
 **/
@FragmentScope
public class NewsVideoPresenter extends BasePresenter<NewsContract.Model,NewsVideoContract.View> {

    @Inject
    public NewsVideoPresenter(NewsContract.Model model, NewsVideoContract.View rootView) {
        super(model, rootView);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
