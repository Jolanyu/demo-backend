package com.gondor.isildur.util;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Specify the corresponding property of the DTO Class to convert to for the
 * toDTO() method.
 */
@Target(java.lang.annotation.ElementType.FIELD)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface DTOProperty {

  String name() default "";

  boolean ignore() default false;

}