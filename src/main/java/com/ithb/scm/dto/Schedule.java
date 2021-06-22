package com.ithb.scm.dto;

import lombok.Data;

import javax.json.bind.annotation.JsonbNumberFormat;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonbPropertyOrder({
    "vehiclePoliceNumber",
    "vehicleCapacity",
    "capacityLoaded",
    "percentageOfCapacityUsage",
    "vehicleCost",
    "traveledDistance",
    "totalCost",
    "costRate",
    "routesOrder"
})
public class Schedule {
  private String vehiclePoliceNumber;
  private Integer vehicleCapacity;
  private Integer capacityLoaded = 0;
  @JsonbNumberFormat(value = "#00.00")
  private Double percentageOfCapacityUsage;
  @JsonbNumberFormat(value = "#00.00")
  private Double traveledDistance = 0.00;
  private Integer vehicleCost;
  @JsonbNumberFormat(value = "#00.00")
  private Double totalCost;
  @JsonbNumberFormat(value = "#00.00")
  private Double costRate;
  private List<Integer> routesOrder = new ArrayList<>();
}
