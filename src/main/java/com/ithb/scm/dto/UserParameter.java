package com.ithb.scm.dto;

import lombok.Data;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

@Data
public class UserParameter {
  @QueryParam
  private String date;

  @QueryParam
  private Integer population;
}
