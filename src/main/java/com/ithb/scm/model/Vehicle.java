package com.ithb.scm.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Vehicle {
  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "type")
  private String type;

  @Column(name = "police_number")
  private String policeNumber;

  @Column(name = "capacity")
  private Integer capacity;

  @Column(name = "vehicle_cost")
  private Integer vehicleCost;
}
