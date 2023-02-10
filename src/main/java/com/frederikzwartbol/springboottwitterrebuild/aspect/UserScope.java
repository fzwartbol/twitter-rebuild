package com.frederikzwartbol.springboottwitterrebuild.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserScope {
    enum value {
        PRIVATE, ADMIN
    }
}
