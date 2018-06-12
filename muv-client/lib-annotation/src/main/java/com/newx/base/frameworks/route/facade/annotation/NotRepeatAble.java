package com.newx.base.frameworks.route.facade.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Created by xuzhijian on 2018/4/13 0013.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface NotRepeatAble {
}
