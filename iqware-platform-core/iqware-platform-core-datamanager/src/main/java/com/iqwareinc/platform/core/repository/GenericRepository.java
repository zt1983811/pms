package com.iqwareinc.platform.core.repository;

import java.io.Serializable;
import java.util.List;

import com.iqwareinc.platform.core.model.entity.BaseEntity;

public interface GenericRepository<ID_T extends Serializable, T extends BaseEntity<ID_T>> {

   public T find(ID_T id);

   public T persist(T entity);

   public List<T> findAll();
}
