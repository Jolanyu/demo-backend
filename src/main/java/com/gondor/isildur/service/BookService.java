package com.gondor.isildur.service;

import com.alibaba.fastjson.JSONObject;
import com.gondor.isildur.entity.Book;

public interface BookService extends BaseService<Book, Long> {
  public JSONObject create(Book book);

  public JSONObject put(long bookId, Book book);

  public JSONObject searchByBookTitle(String bookTitle);

}
