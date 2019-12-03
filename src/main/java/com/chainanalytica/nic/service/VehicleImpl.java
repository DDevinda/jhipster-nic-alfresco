package com.chainanalytica.nic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chainanalytica.nic.repository.VehicleRepository;
import com.chainanalytica.nic.service.dto.Vehicle;

@Service
public class VehicleImpl implements VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;
	
	@Override
	public List<Vehicle> findAll() {
		return vehicleRepository.findAll();
	}
	
	@Override
	public List<Vehicle> getVehicleAndMake() {
		return vehicleRepository.getVehicleAndMake();
	}

	@Override
	public void save(Vehicle theVehicle) {
		vehicleRepository.save(theVehicle);
	}

	@Override
	public Vehicle findById(int theId) {
		
		Optional<Vehicle> result = vehicleRepository.findById(theId);
		if(!result.isPresent()) {
			throw new RuntimeException("Could not find the vehicle");
		}
		
		return result.get();
	}
	

	
}
