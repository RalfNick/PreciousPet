package com.ralf.module_community.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;

import com.rockerhieu.emojicon.EmojiconEditText;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name EditTextMaxLength
 * @email -
 * @date 2019/03/08 上午11:10
 **/
public class EditTextMaxLength {

    public static boolean isCheckEmojicon = true;
    public static int DongTaiFabu = 140;//动态发布页面字数限制
    public static int PindapTieziFabu = 800;//频道帖子发布
    public static int PingLunAndHuiFu = 70;//评论或回复
    public static int LinYangAndPeidui = 300;//领养或配对
    public static int LinyangMiaoshu = 100;//领养描述


    public static void setMaxLength(EmojiconEditText editText, int maxLength) {
        InputFilter filter = new InputFilter.LengthFilter(maxLength);
        InputFilter inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                Log.d("最大长度", "filter: ----->" + charSequence.toString()
                        + "--->" + i + "----->" + i1 + "---->"
                        + spanned + "--->" + i2 + "---->" + i3);
                int length = maxLength - (i2 + i1);
                if (length < 4) {
                    isCheckEmojicon = false;
                } else {
                    isCheckEmojicon = true;
                }
                return null;
            }
        };
        editText.setFilters(new InputFilter[]{inputFilter, filter});
    }

}
