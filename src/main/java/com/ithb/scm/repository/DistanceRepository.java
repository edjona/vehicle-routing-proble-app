package com.ithb.scm.repository;

import com.ithb.scm.model.Distance;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DistanceRepository implements PanacheRepository<Distance> {
  public Distance findByFromAndTo(int from, int to) {
    return find("start = ?1 and finish = ?2", from, to).firstResult();
  }
}
