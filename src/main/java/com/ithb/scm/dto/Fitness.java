package com.ithb.scm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbNumberFormat;
import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;

@Data
@NoArgsConstructor
@JsonbPropertyOrder({"accumulatedCapacityLoaded", "accumulatedVehiclesCapacity", "capacityFitnessValue"})
public class Fitness {
  @JsonbTransient
  private Integer accumulatedVehiclesCapacity;
  @JsonbTransient
  private Integer accumulatedCapacityLoaded;
  @JsonbNumberFormat(value = "#00.00")
  private Double capacityFitnessValue;
  @JsonbNumberFormat(value = "#00.00")
  private Double costFitnessValue;
  @JsonbNumberFormat(value = "#00.00")
  private Double totalFitnessValue;
}
