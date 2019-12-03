package com.chainanalytica.nic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chainanalytica.nic.service.dto.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

	@Query(value="select make,numberPlate from Vehicle")
	List<Vehicle> getVehicleAndMake();
	
}
