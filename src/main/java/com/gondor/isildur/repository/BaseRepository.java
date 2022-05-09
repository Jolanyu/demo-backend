package com.gondor.isildur.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseRepository<T, K>  extends PagingAndSortingRepository<T,K>{
  
}
