package com.ithb.scm.service;

import com.ithb.scm.dto.Result;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class ChromosomeService {
  @Inject
  DistanceService distanceService;

  public void process(Result result) {
    var accumulatedVehiclesCapacity = new AtomicInteger();
    var accumulatedCapacityLoaded = new AtomicInteger();

    result.getChromosomeList().forEach(chromosome -> {
      chromosome.getSchedules().forEach(schedule -> {
        double mileage = calculateDistance(schedule.getRoutesOrder());
        schedule.setTraveledDistance(mileage);
        accumulatedVehiclesCapacity.addAndGet(schedule.getVehicleCapacity());
        accumulatedCapacityLoaded.addAndGet(schedule.getCapacityLoaded());
        double totalCost = schedule.getTraveledDistance() * schedule.getVehicleCost();
        schedule.setTotalCost(totalCost);

        if (result.getHighestCost() < totalCost) {
          result.setHighestCost(totalCost);
        }
      });

      chromosome.getFitness().setAccumulatedVehiclesCapacity(accumulatedVehiclesCapacity.get());
      chromosome.getFitness().setAccumulatedCapacityLoaded(accumulatedCapacityLoaded.get());
      double capacityFitnessValue = 100 - (((double) accumulatedCapacityLoaded.get() / accumulatedVehiclesCapacity.get()) * 100);
      chromosome.getFitness().setCapacityFitnessValue(capacityFitnessValue);
    });

    result.getChromosomeList().forEach(chromosome -> chromosome.getSchedules().forEach(schedule -> {
      double rate = schedule.getTotalCost() / result.getHighestCost() * 100;
      schedule.setCostRate(rate);
    }));

    AtomicReference<Double> costTemp = new AtomicReference<>(0.0);
    result.getChromosomeList().forEach(chromosome -> {
      chromosome.getSchedules().forEach(schedule -> costTemp.updateAndGet(value -> value + schedule.getCostRate()));
      chromosome.getFitness().setCostFitnessValue(costTemp.get() / chromosome.getSchedules().size());
      double totalFitnessValue = chromosome.getFitness().getCostFitnessValue() + chromosome.getFitness()
          .getCapacityFitnessValue();
      chromosome.getFitness().setTotalFitnessValue(totalFitnessValue);
    });
  }

  private double calculateDistance(List<Integer> routes) {
    var temp = 0.0;

    if (routes.size() == 1) {
      return (distanceService.findByFromAndTo(0, routes.get(0)).getKilometers() * 2);
    }

    for (var i = 0; i < routes.size(); i++) {
      if (i == 0) {
        temp += distanceService.findByFromAndTo(0, routes.get(i)).getKilometers();
      }

      if (i == routes.size() - 1) {
        temp += distanceService.findByFromAndTo(routes.get(i), 0).getKilometers();
      }

      if (i > 0 && i < routes.size()) {
        temp += distanceService.findByFromAndTo(routes.get(i - 1), routes.get(i)).getKilometers();
      }
    }

    return temp;
  }
}
