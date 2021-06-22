package com.ithb.scm.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Customer {
  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;
}
