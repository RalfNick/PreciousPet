package com.ralf.module_community.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.ralf.module_community.R;
import com.ralf.module_community.dg.component.DaggerCommunityComponent;
import com.ralf.module_community.dg.module.CommunityModule;
import com.ralf.module_community.mvp.contact.CommunityContact;
import com.ralf.module_community.mvp.presenter.CommunityPresenter;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommunityFragment
 * @email -
 * @date 2018/12/15 下午8:48
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_PATH)
public class CommunityFragment extends BaseFragment<CommunityPresenter> implements CommunityContact.View {

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

        DaggerCommunityComponent.builder()
                .appComponent(appComponent)
                .communityModule(new CommunityModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community, container, false);
        return view;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
