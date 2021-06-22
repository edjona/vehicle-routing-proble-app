package com.ithb.scm.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbNumberFormat;
import javax.json.bind.annotation.JsonbPropertyOrder;
import java.util.List;

@Data
@NoArgsConstructor
@JsonbPropertyOrder({"highestCost"})
public class Result {
  @JsonbNumberFormat(value = "#00.00")
  private Double highestCost = 0.0;
  private List<Chromosome> chromosomeList;
}
