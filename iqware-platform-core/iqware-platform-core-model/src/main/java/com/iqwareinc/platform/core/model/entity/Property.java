package com.iqwareinc.platform.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PMS_PROPERTY")
public class Property extends BaseEntity<Long> {

   private static final long serialVersionUID = 1L;

   @Column(name = "name")
   private String            name;

   public Property() {
      
   }
   
   public Property(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
