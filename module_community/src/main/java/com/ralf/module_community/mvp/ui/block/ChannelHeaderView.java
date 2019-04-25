package com.ralf.module_community.mvp.ui.block;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.di.scope.FragmentScope;
import com.ralf.module_community.R;
import com.ralf.module_community.entity.ChannelTypeEntity;
import com.ralf.module_community.mvp.ui.adapter.ChannelHeaderAdapter;
import com.ralf.pet_provider.router.RouterConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelHeaderView
 * @email -
 * @date 2019/04/23 下午7:21
 **/
@FragmentScope
public class ChannelHeaderView implements View.OnClickListener {

    private ChannelHeaderAdapter mHeaderAdapter;
    private List<ChannelTypeEntity> mEntityList = new ArrayList<>();

    @Inject
    public ChannelHeaderView() {
    }

    public void init(Context context, View headerView) {
        ARouter.getInstance().inject(this);
        mHeaderAdapter = new ChannelHeaderAdapter(R.layout.item_header_channel_type, mEntityList);
        RecyclerView recyclerView = headerView.findViewById(R.id.header_channel_recycler_view);
        TextView moreTv = headerView.findViewById(R.id.header_channel_more_text);
        moreTv.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(mHeaderAdapter);
        mHeaderAdapter.setOnItemClickListener((adapter, view, position) -> {
            int channelId = mEntityList.get(position).getChannelId();
            ARouter.getInstance()
                    .build(RouterConfig.CommunityModule.COMMUNITY_CHANNEL_DETAIL_PATH)
                    .withInt(RouterConfig.CommunityModule.KEY_CHANNEL_DETAIL_ID, channelId)
                    .navigation();
        });
    }

    public void setData(List<ChannelTypeEntity> list) {
        if (list != null && list.size() > 0) {
            mEntityList.clear();
            mEntityList.addAll(list);
            mHeaderAdapter.setNewData(mEntityList);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.header_channel_more_text) {
            ARouter.getInstance()
                    .build(RouterConfig.CommunityModule.COMMUNITY_ALL_CHANNEL_PATH)
                    .navigation();
        }
    }
}
