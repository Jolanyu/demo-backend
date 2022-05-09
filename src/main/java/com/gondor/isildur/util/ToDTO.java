package com.gondor.isildur.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Specify the corresponding DTO class to convert to for the toDTO() method.
 */
@Target(ElementType.TYPE)
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface ToDTO {

  Class<?> DTOClass();

}