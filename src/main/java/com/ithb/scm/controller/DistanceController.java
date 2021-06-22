package com.ithb.scm.controller;

import com.ithb.scm.model.Distance;
import com.ithb.scm.service.DistanceService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/distances")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DistanceController {
  @Inject
  DistanceService distanceService;

  @GET
  public Response getDistanceByFromAndTo(@QueryParam("from") int from, @QueryParam("to") int to) {
    Distance distance = distanceService.findByFromAndTo(from, to);

    if (distance == null) {
      return Response.noContent().build();
    }

    return Response.ok(distance).build();
  }
}
