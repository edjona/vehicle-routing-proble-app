package com.ithb.scm.model;

import lombok.Data;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Demand {
  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "date")
  @JsonbDateFormat(value = "yyyy-MM-dd")
  private Date date;

  @Column(name = "quantity")
  private Integer quantity;

  @Column(name = "route")
  private String route;

  @Column(name = "id_vehicle")
  private Integer vehicleId;

  @Column(name = "id_customer")
  private Integer customerId;
}
