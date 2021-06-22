package com.ithb.scm.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Distance {
  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "start")
  private Integer start;

  @Column(name = "finish")
  private Integer finish;

  @Column(name = "kilometers")
  private Double kilometers;
}
