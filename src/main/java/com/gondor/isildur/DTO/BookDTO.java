package com.gondor.isildur.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BookDTO extends BaseDTO {
  private long bookId;
  private int bookStatus;
  private Long gradeId;
  private String bookIsbn;
  private String bookTitle;
  private String bookImage;
  private String bookAuthor;
  private String bookTranslator;
  private String bookCategory;
  private String bookPublisher;
  private String bookSummary;
  private long adminId;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
  private java.sql.Timestamp createTime;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") 
  private java.sql.Timestamp updateTime;
}
