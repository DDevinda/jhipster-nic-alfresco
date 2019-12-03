package com.chainanalytica.nic.service;

import java.util.List;

import com.chainanalytica.nic.service.dto.Vehicle;

public interface VehicleService {

	public List<Vehicle> findAll();
	public List<Vehicle> getVehicleAndMake();
	public Vehicle findById(int theId);
	public void save(Vehicle theVehicle);
	
	
}
