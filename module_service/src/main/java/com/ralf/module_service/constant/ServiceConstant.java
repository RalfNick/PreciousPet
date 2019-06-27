package com.ralf.module_service.constant;

import com.ralf.module_service.R;
import com.ralf.pet_provider.router.RouterConfig;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ServiceConstant
 * @email -
 * @date 2019/06/16 11:48
 **/
public class ServiceConstant {

    public static final String[] TITLE = {"专家在线", "同城服务", "宠物领养", "萌宠配对", "商家入驻", "专家认证"};
    public static final String[] DES = {"宠物专家为您解答各类养宠疑问", "最齐全的宠物服务就在您身边",
            "为您免费提供宠物领养", "宠物行业人认证通道", "便捷高效的同城服务入驻渠道", "宠物行业人认证通道"};
    public static final int[] ICON =
            {
                    R.mipmap.zhuanjia_zaixian,
                    R.mipmap.tongcheng,
                    R.mipmap.icon_adoption_pet,
                    R.mipmap.pet_pair,
                    R.mipmap.shangjia_ruzhu,
                    R.mipmap.zhuanjia_renzheng};
    public static final String[] PATH = {
            RouterConfig.ServiceModule.PATH_EXPERT_ONLINE,
            RouterConfig.ServiceModule.PATH_SAME_CITY,
            RouterConfig.ServiceModule.PATH_PET_ADOPTION,
            RouterConfig.ServiceModule.PATH_PET_PAIR,
            RouterConfig.Provider.WEB_VIEW_PATH,
            RouterConfig.Provider.WEB_VIEW_PATH};


}
