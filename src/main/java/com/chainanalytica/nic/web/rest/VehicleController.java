package com.chainanalytica.nic.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chainanalytica.nic.service.VehicleService;
import com.chainanalytica.nic.service.dto.Vehicle;

@CrossOrigin
@RestController
@RequestMapping("api/v1")
public class VehicleController {

	@Autowired
	VehicleService vehicleService;
	
	@GetMapping("/vehicles")
	public List<Vehicle> getAllVehicles() {
		return vehicleService.findAll();
	}
	
	@GetMapping("/vehicle/{vehicleId}")
	public Vehicle getVehicle(@PathVariable int vehicleId) {
		return vehicleService.findById(vehicleId);
	}
	
	@PostMapping("/vehicles")
	public Vehicle addVehicle(@RequestBody Vehicle theVehicle) {
		theVehicle.setId(0);
		vehicleService.save(theVehicle);
		return theVehicle;
	}
	
}
