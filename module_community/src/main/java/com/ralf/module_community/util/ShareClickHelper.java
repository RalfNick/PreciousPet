package com.ralf.module_community.util;

import android.content.Context;
import android.text.TextUtils;

import com.ralf.module_community.entity.DynamicEntity;
import com.ralf.pet_provider.http.HttpUrl;
import com.ralf.pet_provider.share.PetShare;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ShareClickHelper
 * @email -
 * @date 2019/03/09 下午2:31
 **/
public class ShareClickHelper {

    /**
     * 分享社区状态
     *
     * @param dynamicEntity 实体类
     */
    public static void shareCommunity(Context context, DynamicEntity dynamicEntity) {
        String url = String.format(HttpUrl.COMMUNITY_SHARE_URL, dynamicEntity.getDynamicId());
        String nickName = dynamicEntity.getNickName();
        String title = TextUtils.isEmpty(nickName) ? "\r" : String.format(PetShare.shareTitleOfCommunity, nickName);
        String desc = !TextUtils.isEmpty(dynamicEntity.getTalk()) ? dynamicEntity.getTalk() : PetShare.SHARE_SELECTED_DESC;
        // 1 视频类型   0 图片类型
        String imgUrl = dynamicEntity.getType() == 1 ? dynamicEntity.getVideoPrintscreen() : dynamicEntity.getDynamicsPath();
        PetShare.ShareBuilder
                .with(context)
                .url(url)
                .imgUrl(imgUrl)
                .title(title)
                .desc(desc)
                .withText(desc)
                .build()
                .shareToOthers(PetShare.ShareTypeEnum.SHARE_COMMUNITY_FEATURED);
    }
}
