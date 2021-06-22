package com.ithb.scm.repository;

import com.ithb.scm.model.Demand;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class DemandRepository implements PanacheRepository<Demand> {
  public List<Demand> findByDate(Date date) {
    return this.list("date", date);
  }
}
