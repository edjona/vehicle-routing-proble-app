package com.ithb.scm.service;

import com.ithb.scm.model.Demand;
import com.ithb.scm.repository.DemandRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@ApplicationScoped
public class DemandService {
  @Inject
  DemandRepository demandRepository;

  public List<Demand> findByDate(String dateString) throws ParseException {
    return demandRepository.findByDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateString));
  }
}
