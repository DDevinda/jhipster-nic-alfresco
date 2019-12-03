package com.chainanalytica.nic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.chainanalytica.nic.repository.CitizenRepository;
import com.chainanalytica.nic.service.dto.Citizen;




@Service
public class CitizenImpl implements CitizenService {
	
	@Autowired 
	CitizenRepository citizenRepository;
	
	@Override
	public List<Citizen> findAll() {
		
		
		return citizenRepository.findAll();
	}

	@Override
	public Citizen findById(int theId) {
		Optional<Citizen> result = citizenRepository.findById(theId);
		if(!result.isPresent()) {
			throw new RuntimeException("could not find the Citizen");	
		}
		return result.get();
	}

	@Override
	public void save(Citizen theCitizen) {
		citizenRepository.save(theCitizen);
	}

	@Override
	public List<Object[]> getFirstName() {
		return citizenRepository.getFirstNames();
	}
	
	

}
