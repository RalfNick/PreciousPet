package com.ralf.module_news.dg.module;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_news.mvp.contract.PicturePreviewContract;
import com.ralf.module_news.mvp.model.PicturePreviewModel;

import dagger.Module;
import dagger.Provides;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PicturePreviewModule
 * @email -
 * @date 2019/06/01 14:46
 **/
@Module
public class PicturePreviewModule {

    private PicturePreviewContract.View mView;

    public PicturePreviewModule(PicturePreviewContract.View view) {
        mView = view;
    }

    @ActivityScope
    @Provides
    public PicturePreviewContract.View providePicturePreviewView() {
        return mView;
    }

    @ActivityScope
    @Provides
    public PicturePreviewContract.Model providePicturePreviewModel(PicturePreviewModel model) {
        return model;
    }
}
