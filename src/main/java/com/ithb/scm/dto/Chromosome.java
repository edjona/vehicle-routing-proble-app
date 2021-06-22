package com.ithb.scm.dto;

import lombok.Data;

import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@Data
@JsonbPropertyOrder(value = {"chromosomeNumber", "numberOfVehicleUsed", "fitness", "schedules"})
public class Chromosome {
  private Integer chromosomeNumber;
  private Integer numberOfVehicleUsed;
  private Fitness fitness = new Fitness();
  private List<Schedule> schedules;
}
