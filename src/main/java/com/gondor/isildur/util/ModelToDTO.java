package com.gondor.isildur.util;

// public class ModelToDTO {

//   static public <T> T convert(Object model, Class<T> DTOClass) {
//     Object DTOObject = null;
//     try {
//       DTOObject = DTOClass.getConstructor().newInstance();
//       Class<?> modelClass = model.getClass();
//       for (Field field : modelClass.getDeclaredFields()) {
//         String fieldName;
//         if (field.getAnnotation(DTOProperty.class) != null) {
//           if (field.getAnnotation(DTOProperty.class).ignore()) {
//             continue;
//           }
//           fieldName = field.getAnnotation(DTOProperty.class).name();
//         } else {
//           fieldName = field.getName();
//         }
//         Field DTOfield = DTOClass.getDeclaredField(fieldName);
//         DTOfield.setAccessible(true);
//         Field modelField = modelClass.getDeclaredField(field.getName());
//         modelField.setAccessible(true);
//         DTOfield.set(DTOObject, modelField.get(model));
//       }
//     } catch (Exception e) {
//       e.printStackTrace();
//     }
//     return (T) DTOObject;
//   }

// }