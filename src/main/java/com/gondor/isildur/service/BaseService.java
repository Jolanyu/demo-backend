package com.gondor.isildur.service;

import com.alibaba.fastjson.JSONObject;
import com.gondor.isildur.entity.BaseEntity;

public interface BaseService<T extends BaseEntity, K> {

  public JSONObject delete(K id);

  public JSONObject get(K id);

  public JSONObject create(T entity);

  public JSONObject getByPage(int page);

  public JSONObject getByPage(int page, int size);

  public JSONObject getCount();

}
