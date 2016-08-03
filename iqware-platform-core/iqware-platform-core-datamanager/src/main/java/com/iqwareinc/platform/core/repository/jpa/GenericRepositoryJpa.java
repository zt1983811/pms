package com.iqwareinc.platform.core.repository.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.iqwareinc.platform.core.model.entity.BaseEntity;
import com.iqwareinc.platform.core.repository.GenericRepository;

public abstract class GenericRepositoryJpa<ID_T extends Serializable, T extends BaseEntity<ID_T>> implements GenericRepository<ID_T, T> {

   protected Class<ID_T>   idClass;

   protected Class<T>      entityClass;

   @PersistenceContext
   protected EntityManager entityManager;

   @SuppressWarnings({ "unchecked", "rawtypes" })
   protected GenericRepositoryJpa() {
      Type genericSuperclass = this.getClass().getGenericSuperclass();
      while (!(genericSuperclass instanceof ParameterizedType)) {
         if (!(genericSuperclass instanceof Class))
            throw new IllegalStateException("Unable to determine type " + "arguments because generic superclass neither " + "parameterized type nor class.");
         if (genericSuperclass == GenericRepositoryJpa.class)
            throw new IllegalStateException("Unable to determine type " + "arguments because no parameterized generic superclass " + "found.");
         genericSuperclass = ((Class)genericSuperclass).getGenericSuperclass();
      }
      ParameterizedType type = (ParameterizedType)genericSuperclass;
      Type[] arguments = type.getActualTypeArguments();
      this.idClass = (Class<ID_T>)arguments[0];
      this.entityClass = (Class<T>)arguments[1];
   }

   @Override
   public T find(ID_T id) {
      if (id == null) {
         return null;
      }
      return entityManager.find(entityClass, id);
   }

   @Override
   public T persist(T entity) {
      entityManager.persist(entity);
      return entity;
   }

   @Override
   public List<T> findAll() {
      CriteriaBuilder cb = entityManager.getCriteriaBuilder();
      CriteriaQuery<T> criteria = cb.createQuery(entityClass);
      Root<T> root = criteria.from(entityClass);
      criteria.select(root);
      return entityManager.createQuery(criteria).getResultList();
   }
}