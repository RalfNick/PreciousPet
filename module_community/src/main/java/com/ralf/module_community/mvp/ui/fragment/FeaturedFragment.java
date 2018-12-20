package com.ralf.module_community.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseLazyFragment;
import com.jess.arms.di.component.AppComponent;
import com.ralf.module_community.R;
import com.ralf.module_community.dg.component.DaggerFeaturedComponent;
import com.ralf.module_community.dg.module.FeaturedModule;
import com.ralf.module_community.mvp.contact.FeaturedContact;
import com.ralf.module_community.mvp.presenter.FeaturedPresenter;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FeaturedFragment
 * @email -
 * @date 2018/12/15 下午8:48
 **/
public class FeaturedFragment extends BaseLazyFragment<FeaturedPresenter> implements FeaturedContact.View {

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

        DaggerFeaturedComponent.builder()
                .appComponent(appComponent)
                .featuredModule(new FeaturedModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_featured, container, false);
    }

    @Override
    protected void loadLargeData() {

    }

    @Override
    protected void loadSmallData() {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
