package com.ralf.module_community.mvp.ui.provider;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.jess.arms.utils.ToastUtils;
import com.ralf.module_community.R;
import com.ralf.module_community.constant.MultiItemType;
import com.ralf.module_community.entity.AdapterMultiItemEntity;
import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.module_community.entity.PraiseEntity;
import com.ralf.module_community.mvp.ui.view.FeaturedPersonView;
import com.rockerhieu.emojicon.EmojiconTextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ItemContentProvider
 * @email -
 * @date 2018/12/27 下午1:18
 **/
public class ItemFooterProvider extends BaseItemProvider<AdapterMultiItemEntity, BaseViewHolder> {

    private FeaturedPersonView mPersonView;

    @Override
    public int viewType() {
        return MultiItemType.TYPE_FOOTER;
    }

    @Override
    public int layout() {
        return R.layout.item_featured_footer_layout;
    }

    @Override
    public void convert(BaseViewHolder helper, AdapterMultiItemEntity data, int position) {
        DynamicEntity entity = data.getDynamicBean();
        Integer ownPraise = entity.getOwnPraise();
        Integer bePraiseTimes = data.getDynamicBean().getBePraiseTimes();

        mPersonView = helper.getView(R.id.item_footer_person_view);
        // 点赞总人数
        TextView personNumTv = helper.getView(R.id.item_footer_person_num);
        if (bePraiseTimes < 1) {
            personNumTv.setVisibility(View.GONE);
        } else {
            personNumTv.setVisibility(View.VISIBLE);
            personNumTv.setText(String.valueOf(bePraiseTimes));
        }
        // 点赞，送礼，评论，分享
        RadioButton supportButton = helper.getView(R.id.item_footer_support_rb);
        RadioButton giftButton = helper.getView(R.id.item_footer_gift_rb);
        RadioButton commentButton = helper.getView(R.id.item_footer_comment_rb);
        RadioButton shareButton = helper.getView(R.id.item_footer_share_rb);
        // 评论消息显示
        LinearLayout commentLayout = helper.getView(R.id.item_footer_comment_ll);
        EmojiconTextView firstMsgTv = helper.getView(R.id.item_footer_comment_first);
        EmojiconTextView secondMsgTv = helper.getView(R.id.item_footer_comment_second);
        EmojiconTextView thirdMsgTv = helper.getView(R.id.item_footer_comment_third);
        // 查看更多评论
        TextView msgMoreTv = helper.getView(R.id.item_footer_comment_more);
        setHeadPortraitData(entity);
    }

    /**
     * 判断点赞还是取消赞
     *
     * @param ownPraise
     * @return
     */
    private boolean makeSureZanOrCancel(int ownPraise) {
        switch (ownPraise) {
            case 0:
                //取消点赞
                return false;
            case 1:
                //点赞
                return true;
            default:
        }
        return false;
    }

    /**
     * 加载头像数据
     *
     * @param entity
     */
    private void setHeadPortraitData(DynamicEntity entity) {
        List<PraiseEntity> praiseList = entity.getPraiseList();
        Map<Integer, String> headDataMap = new HashMap<>();
        if (praiseList != null && praiseList.size() > 0) {
            for (PraiseEntity bean : praiseList) {
                headDataMap.put(bean.getUserId(), bean.getHeadPortrait());
            }
        }
        mPersonView.setClickListener(new FeaturedPersonView.OnHeadPortraitClickListener() {
            @Override
            public void onClick(int userId) {
                ToastUtils.showShort("跳转到用户界面 " + userId);
            }
        });
        mPersonView.setHeadPortaitData(headDataMap);
    }
}
