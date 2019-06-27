package com.ralf.module_service.mvp.ui.provider;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.jess.arms.utils.SizeUtils;
import com.jess.arms.utils.ToastUtils;
import com.jess.arms.widget.decoration.LinearOffsetsItemDecoration;
import com.ralf.module_service.R;
import com.ralf.module_service.entity.ExpertOnlineEntity.ExpertsListBean;
import com.ralf.module_service.entity.ExpertOnlineMultiEntity;
import com.ralf.module_service.entity.ExpertOnlineMultiType;
import com.ralf.pet_provider.util.ImageLoaderHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemOptionProvider
 * @email -
 * @date 2019/06/25 14:55
 **/
public class ItemExpertProvider extends BaseItemProvider<ExpertOnlineMultiEntity, BaseViewHolder> {

    private List<ExpertsListBean> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;

    @Override
    public int viewType() {
        return ExpertOnlineMultiType.TYPE_EXPERT;
    }

    @Override
    public int layout() {
        return R.layout.item_expert_recommend_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, ExpertOnlineMultiEntity data, int position) {
        List<ExpertsListBean> expertsList = data.getData().getExpertsList();
        if (expertsList != null && expertsList.size() > 0) {
            mList.clear();
            mList.addAll(expertsList);
        }

        if (mRecyclerView == null){
            mRecyclerView = helper.getView(R.id.expert_recommend_recycler_view);
            BaseQuickAdapter<ExpertsListBean, BaseViewHolder> adapter = new BaseQuickAdapter<ExpertsListBean, BaseViewHolder>(R.layout.child_expert_recommend_layout, mList) {
                @Override
                protected void convert(BaseViewHolder helper, ExpertsListBean item) {
                    helper.setText(R.id.expert_name_tv, item.getUserName());
                    ImageView headImg = helper.getView(R.id.expert_head_image_iv);
                    ImageLoaderHelper.loadImage(mContext,headImg,item.getHeadPortrait(),true);
                    helper.getView(R.id.expert_name_tv).setOnClickListener(mClickListener);
                    helper.getView(R.id.expert_area_tv).setOnClickListener(mClickListener);
                    helper.getView(R.id.expert_head_image_iv).setOnClickListener(mClickListener);
                    helper.getView(R.id.ask_question_btn).setOnClickListener(mClickListener);
                }
            };
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mRecyclerView.setAdapter(adapter);
            LinearOffsetsItemDecoration decoration = new LinearOffsetsItemDecoration(LinearOffsetsItemDecoration.LINEAR_OFFSETS_HORIZONTAL);
            decoration.setItemOffsets(SizeUtils.dp2px(20));
            mRecyclerView.addItemDecoration(decoration);
            adapter.notifyDataSetChanged();
        }
    }

    private View.OnClickListener mClickListener = view -> {
        int viewId = view.getId();
        if (viewId == R.id.expert_name_tv || viewId == R.id.expert_area_tv
                || viewId == R.id.expert_head_image_iv) {
            ToastUtils.showShort("医生主页");
        } else if (viewId == R.id.ask_question_btn) {
            ToastUtils.showShort("询问医生");
        }
    };
}
