package com.ralf.module_news.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_news.mvp.contract.NewsImportantContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendPresenter
 * @email -
 * @date 2019/05/15 19:35
 **/
@FragmentScope
public class NewsImportantPresenter extends BasePresenter<NewsImportantContract.Model,NewsImportantContract.View> {

    @Inject
    public NewsImportantPresenter(NewsImportantContract.Model model, NewsImportantContract.View rootView) {
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
