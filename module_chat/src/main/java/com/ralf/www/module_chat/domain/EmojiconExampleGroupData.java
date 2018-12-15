package com.ralf.www.module_chat.domain;

import com.hyphenate.easeui.domain.EaseEmojicon;
import com.hyphenate.easeui.domain.EaseEmojicon.Type;
import com.hyphenate.easeui.domain.EaseEmojiconGroupEntity;
import com.jess.arms.integration.AppManager;
import com.ralf.www.module_chat.R;

import java.util.Arrays;

public class EmojiconExampleGroupData {

    private static int[] icons = new int[]{
            R.drawable.d_anjing,
            R.drawable.d_baibai,
            R.drawable.d_bishi,
            R.drawable.d_daku,
            R.drawable.d_daxiao,
            R.drawable.d_fendou,
            R.drawable.d_haqian,
            R.drawable.d_heilian,
            R.drawable.d_huaixiao,
            R.drawable.d_huaxin,
            R.drawable.d_jiujie,
            R.drawable.d_kaixin,
            R.drawable.d_koubi,
            R.drawable.d_kunkun,
            R.drawable.d_kuxiao,
            R.drawable.d_leiben,
            R.drawable.d_liuhan,
            R.drawable.d_mihu,
            R.drawable.d_mojing,
            R.drawable.d_nanguo,
            R.drawable.d_paishou,
            R.drawable.d_qinqin,
            R.drawable.d_shiwang,
            R.drawable.d_shuijiao,
            R.drawable.d_touxiao,
            R.drawable.d_weiqu,
            R.drawable.d_weixiao,
            R.drawable.d_wenhao,
            R.drawable.d_xieyan,
            R.drawable.d_yinxian
    };

    //    private static int[] bigIcons = new int[]{
    //        R.drawable.icon_002,
    //        R.drawable.icon_007,
    //        R.drawable.icon_010,
    //        R.drawable.icon_012,
    //        R.drawable.icon_013,
    //        R.drawable.icon_018,
    //        R.drawable.icon_019,
    //        R.drawable.icon_020,
    //        R.drawable.icon_021,
    //        R.drawable.icon_022,
    //        R.drawable.icon_024,
    //        R.drawable.icon_027,
    //        R.drawable.icon_029,
    //        R.drawable.icon_030,
    //        R.drawable.icon_035,
    //        R.drawable.icon_040,
    //    };


    private static final EaseEmojiconGroupEntity DATA = createData();

    private static EaseEmojiconGroupEntity createData() {
        EaseEmojiconGroupEntity emojiconGroupEntity = new EaseEmojiconGroupEntity();
        EaseEmojicon[] datas = new EaseEmojicon[icons.length];
        for (int i = 0; i < icons.length; i++) {
            datas[i] = new EaseEmojicon(icons[i], null, Type.BIG_EXPRESSION);
            datas[i].setBigIcon(icons[i]);
            //you can replace this to any you want
            datas[i].setName(AppManager.getAppManager().getApplication().getString(R.string.emojicon_test_name) + (i + 1));
            datas[i].setIdentityCode("em" + (1000 + i + 1));
        }
        emojiconGroupEntity.setEmojiconList(Arrays.asList(datas));
        emojiconGroupEntity.setIcon(R.drawable.d_huaxin);
        emojiconGroupEntity.setType(Type.BIG_EXPRESSION);
        return emojiconGroupEntity;
    }


    public static EaseEmojiconGroupEntity getData() {
        return DATA;
    }
}
