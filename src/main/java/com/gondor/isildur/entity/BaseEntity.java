package com.gondor.isildur.entity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.gondor.isildur.util.DTOProperty;
import com.gondor.isildur.util.ToDTO;

public class BaseEntity {

  /**
   * Convert this entity to a DTO.
   */
  public <T> T toDTO() {
    Object DTOObject = null;
    try {
      Class<?> selfClass = this.getClass();
      if (selfClass.getAnnotation(ToDTO.class) == null) {
        throw new Exception("ToDTO annotation not found");
      }
      if (selfClass.getAnnotation(ToDTO.class).DTOClass() == null) {
        throw new Exception("DTOClass not found");
      }
      Class<?> DTOClass = selfClass.getDeclaredAnnotation(ToDTO.class).DTOClass();

      DTOObject = DTOClass.getConstructor().newInstance();
      for (Field field : selfClass.getDeclaredFields()) {
        String fieldName;
        if (field.getAnnotation(DTOProperty.class) != null) {
          if (field.getAnnotation(DTOProperty.class).ignore()) {
            continue;
          }
          fieldName = field.getAnnotation(DTOProperty.class).name();
        } else {
          fieldName = field.getName();
        }
        Field DTOfield = DTOClass.getDeclaredField(fieldName);
        DTOfield.setAccessible(true);
        Field modelField = selfClass.getDeclaredField(field.getName());
        modelField.setAccessible(true);
        DTOfield.set(DTOObject, modelField.get(this));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return (T) DTOObject;
  }

  public static <E extends BaseEntity, T> List<T> toDTO(List<E> entities, Class<T> T) {
    List<T> DTOs = new ArrayList<>();
    for (E entity : entities) {
      DTOs.add(entity.toDTO());
    }
    return DTOs;
  }
}
