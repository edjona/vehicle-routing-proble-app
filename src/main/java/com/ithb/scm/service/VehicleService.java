package com.ithb.scm.service;

import com.ithb.scm.model.Vehicle;
import com.ithb.scm.repository.VehicleRepository;
import com.ithb.scm.util.ListUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.*;

@ApplicationScoped
public class VehicleService {
  @Inject
  VehicleRepository vehicleRepository;

  public List<List<Vehicle>> getRandomVehicles(int population) {
    List<Vehicle> vehicleList = vehicleRepository.listAll();
    return buildFrom(population, vehicleList);
  }

  private List<List<Vehicle>> buildFrom(int population, List<Vehicle> vehicleList) {
    List<List<Vehicle>> listOfVehicleList = new ArrayList<>();
    ListUtility<Vehicle> utility = new ListUtility<>();

    for (var i = 0; i < population; i++) {
      listOfVehicleList.add(utility.immutableRandom(subListing(vehicleList)));
    }

    return listOfVehicleList;
  }

  private List<Vehicle> subListing(List<Vehicle> vehicles) {
    var random = new Random().nextInt(vehicles.size());
    return vehicles.subList(0, useOneIfGetZeroFrom(random));
  }

  private int useOneIfGetZeroFrom(int random) {
    return random == 0 ? 1 : random;
  }
}
