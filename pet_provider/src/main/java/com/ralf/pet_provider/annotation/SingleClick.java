package com.ralf.pet_provider.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name SingleClick
 * @email -
 * @date 2019/02/23 下午9:00
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SingleClick {
    /* 点击间隔时间 */
    long value() default 1000;
}
