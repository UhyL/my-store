package com.nju.mystore.util;


import com.nju.mystore.enums.RoleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessUtil {
    RoleEnum[] roles()default {RoleEnum.STAFF, RoleEnum.MANAGER, RoleEnum.CEO, RoleEnum.CUSTOMER};
}
