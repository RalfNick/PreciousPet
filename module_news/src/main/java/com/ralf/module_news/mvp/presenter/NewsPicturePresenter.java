package com.ralf.module_news.mvp.presenter;

import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BasePresenter;
import com.ralf.module_news.mvp.contract.NewsPictureContract;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendPresenter
 * @email -
 * @date 2019/05/15 19:35
 **/
@FragmentScope
public class NewsPicturePresenter extends BasePresenter<NewsPictureContract.Model,NewsPictureContract.View> {

    @Inject
    public NewsPicturePresenter(NewsPictureContract.Model model, NewsPictureContract.View rootView) {
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
