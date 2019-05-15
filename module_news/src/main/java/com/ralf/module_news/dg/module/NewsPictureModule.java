package com.ralf.module_news.dg.module;

import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_news.mvp.contract.NewsPictureContract;
import com.ralf.module_news.mvp.model.NewsPictureModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsPictureModule
 * @email -
 * @date 2019/05/15 19:41
 **/
@Module
public class NewsPictureModule {

    private NewsPictureContract.View mView;

    public NewsPictureModule(NewsPictureContract.View view) {
        mView = view;
    }

    @FragmentScope
    @Provides
    public NewsPictureContract.View provideNewsPictureView() {
        return mView;
    }

    @FragmentScope
    @Provides
    public NewsPictureContract.Model provideNewsPictureModel(NewsPictureModel model) {
        return model;
    }
}
