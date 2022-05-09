package com.gondor.isildur.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.gondor.isildur.DTO.BaseDTO;
import com.gondor.isildur.entity.BaseEntity;
import com.gondor.isildur.service.BaseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

public class BaseImpl<T extends BaseEntity, K> implements BaseService<T, K> {

  @Autowired
  PagingAndSortingRepository<T, K> repository;

  @Override
  public JSONObject delete(K id) {
    Boolean exist = repository.existsById(id);
    if (!exist) {
      return new BaseDTO().build(1, "not found");
    }
    repository.deleteById(id);
    return new BaseDTO().build();
  }

  @Override
  public JSONObject get(K id) {
    T entity = repository.findById(id).orElse(null);
    if (entity == null) {
      return new BaseDTO().build(1, "not found");
    }
    BaseDTO dto = entity.toDTO();
    return dto.build();
  }

  @Override
  public JSONObject create(T entity) {
    T savedEntity = repository.save(entity);
    BaseDTO dto = savedEntity.toDTO();
    return dto.build();
  }

  @Override
  public JSONObject getByPage(int page) {
    return getByPage(page, 10);
  }

  @Override
  public JSONObject getByPage(int page, int size) {
    List<T> entities = repository.findAll(PageRequest.of(page, size)).getContent();
    if (entities.size() == 0) {
      return new BaseDTO().build(101, "index out of bound");
    }
    List<BaseDTO> entityDTOs = new ArrayList<BaseDTO>();
    for (T entity : entities) {
      entityDTOs.add(entity.toDTO());
    }
    return new BaseDTO().build(entityDTOs);
  }

  @Override
  public JSONObject getCount() {
    Long count = repository.count();
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("count", count);
    return new BaseDTO().build(jsonObject);
  }

}
