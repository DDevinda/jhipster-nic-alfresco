package com.chainanalytica.nic.web.rest;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chainanalytica.nic.security.AuthoritiesConstants;
import com.chainanalytica.nic.service.CitizenService;
import com.chainanalytica.nic.service.dto.Citizen;



@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class CitizenController {
	
	@Autowired
	private CitizenService citizenService;
	
	@GetMapping("/citizens")
	public List<Citizen> getAllCitizens() {
		return citizenService.findAll();
	}
	
	@GetMapping("/citizensfn")
	public List<Object[]> getAllCitizensFn() {
		return citizenService.getFirstName();
	}
	
	@GetMapping("/citizens/{citizenId}")
	public Citizen getCitizen(@PathVariable int citizenId) {
		return citizenService.findById(citizenId);
	}
	
	@PostMapping("/citizens")
	public Citizen addCitizen(@RequestBody Citizen theCitizen) {
		theCitizen.setId(0);
		citizenService.save(theCitizen);
		return theCitizen;	
	}
	
	@PutMapping("/citizens")
	public Citizen UpdateCitizen(@RequestBody Citizen theCitizen) {
		citizenService.save(theCitizen);
		return theCitizen;	
	}

}
