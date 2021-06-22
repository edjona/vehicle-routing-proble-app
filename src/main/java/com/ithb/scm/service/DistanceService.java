package com.ithb.scm.service;

import com.ithb.scm.model.Distance;
import com.ithb.scm.repository.DistanceRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DistanceService {
  @Inject
  DistanceRepository distanceRepository;

  public Distance findByFromAndTo(int from, int to) {
    return distanceRepository.findByFromAndTo(from, to);
  }
}
