package com.ralf.module_community.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.di.component.AppComponent;
import com.ralf.module_community.R;
import com.ralf.pet_provider.base.BaseSwipeBackActivity;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelTopicActivity
 * @email -
 * @date 2019/04/30 15:46
 **/
@Route(path = RouterConfig.CommunityModule.COMMUNITY_CHANNEL_TOPIC_PATH)
public class ChannelTopicActivity extends BaseSwipeBackActivity {

    @Autowired(name = RouterConfig.CommunityModule.KEY_CHANNEL_TOPIC_ID)
    int mTopicId;

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_channel_topic;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
}
