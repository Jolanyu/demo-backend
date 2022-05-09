package com.gondor.isildur.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gondor.isildur.DTO.BookDTO;
import com.gondor.isildur.util.ToDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "book")
@ToDTO(DTOClass = BookDTO.class)
public class Book extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long bookId;
  private String bookIsbn;
  private String bookTitle;
  private String bookImage;
  private String bookAuthor;
  private String bookTranslator;
  private String bookCategory;
  private String bookPublisher;
  private String bookSummary;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
}
