package com.gondor.isildur.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AdminDTO extends BaseDTO {
  private long adminId;
  private String token;
  private String adminName;
  private Integer adminRole;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
   private java.sql.Timestamp createTime;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
   private java.sql.Timestamp updateTime;
}
