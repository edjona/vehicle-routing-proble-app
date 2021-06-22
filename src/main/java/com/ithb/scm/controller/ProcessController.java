package com.ithb.scm.controller;

import com.ithb.scm.dto.Chromosome;
import com.ithb.scm.dto.Result;
import com.ithb.scm.dto.UserParameter;
import com.ithb.scm.service.ChromosomeService;
import com.ithb.scm.service.ScheduleService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.List;

@Path("/run")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProcessController {
  @Inject
  ScheduleService scheduleService;

  @Inject
  ChromosomeService chromosomeService;

  @GET
  public Response run(@BeanParam UserParameter userParameter) throws ParseException {
    Result result = new Result();
    result.setChromosomeList(scheduleService.initialize(userParameter.getPopulation(), userParameter.getDate()));
    chromosomeService.process(result);

    return Response.ok(result).build();
  }
}
