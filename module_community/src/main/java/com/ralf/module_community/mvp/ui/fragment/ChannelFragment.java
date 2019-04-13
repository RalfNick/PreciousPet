package com.ralf.module_community.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseLazyFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.dg.component.DaggerChannelComponent;
import com.ralf.module_community.dg.module.ChannelModule;
import com.ralf.module_community.mvp.contact.ChannelContract;
import com.ralf.module_community.mvp.presenter.ChannelPresenter;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendFragment
 * @email -
 * @date 2019/04/13 上午10:30
 **/
public class ChannelFragment extends BaseLazyFragment<ChannelPresenter> implements ChannelContract.View{

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerChannelComponent.builder()
                .appComponent(appComponent)
                .channelModule(new ChannelModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_channel_layout,container,false);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    protected void loadLargeData() {

    }

    @Override
    protected void initEvent(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        ToastUtils.showShort(message);
    }
}
