package com.ithb.scm.service;

import com.ithb.scm.dto.Chromosome;
import com.ithb.scm.dto.Schedule;
import com.ithb.scm.model.Demand;
import com.ithb.scm.model.Vehicle;
import com.ithb.scm.util.ListUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class ScheduleService {
  @Inject
  VehicleService vehicleService;

  @Inject
  DemandService demandService;

  public List<Chromosome> initialize(int populationCount, String date) throws ParseException {
    List<Chromosome> chromosomeList = new ArrayList<>();
    prepareVehicle(populationCount, chromosomeList);
    prepareRoute(date, chromosomeList);
    prepareCapacityPercentage(chromosomeList);

    return chromosomeList;
  }

  private void prepareCapacityPercentage(List<Chromosome> chromosomeList) {
    chromosomeList.forEach(chromosome -> chromosome.getSchedules().forEach(schedule -> {
      double percentage = ((double) schedule.getCapacityLoaded() / schedule.getVehicleCapacity()) * 100;
      schedule.setPercentageOfCapacityUsage(percentage);
    }));
  }

  private void prepareRoute(String date, List<Chromosome> chromosomeList) throws ParseException {
    ListUtility<Demand> utility = new ListUtility<>();
    List<Demand> demandList = utility.immutableRandom(demandService.findByDate(date));

    chromosomeList.forEach(chromosome -> {
      int vehicleCountInSchedule = chromosome.getSchedules().size();

      demandList.forEach(demand -> {
        var random = new Random().nextInt(vehicleCountInSchedule);
        chromosome.getSchedules().get(random).getRoutesOrder().add(demand.getCustomerId());
        int capacityIncrement = chromosome.getSchedules().get(random).getCapacityLoaded() + demand.getQuantity();
        chromosome.getSchedules().get(random).setCapacityLoaded(capacityIncrement);
      });

    });
  }

  private void prepareVehicle(int populationCount, List<Chromosome> chromosomeList) {
    List<List<Vehicle>> listOfVehicleList = vehicleService.getRandomVehicles(populationCount);

    var index = new AtomicInteger();
    listOfVehicleList.forEach(vehicleList -> {
      List<Schedule> scheduleList = new ArrayList<>();
      vehicleList.forEach(vehicle -> {
        var schedule = new Schedule();
        schedule.setVehiclePoliceNumber(vehicle.getPoliceNumber());
        schedule.setVehicleCapacity(vehicle.getCapacity());
        schedule.setVehicleCost(vehicle.getVehicleCost());
        scheduleList.add(schedule);
      });
      var chromosome = new Chromosome();
      chromosome.setNumberOfVehicleUsed(scheduleList.size());
      chromosome.setSchedules(scheduleList);
      chromosome.setChromosomeNumber(index.getAndIncrement());
      chromosomeList.add(chromosome);
    });
  }
}
