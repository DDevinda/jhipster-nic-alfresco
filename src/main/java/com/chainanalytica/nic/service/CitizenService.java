package com.chainanalytica.nic.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chainanalytica.nic.service.dto.Citizen;




public interface CitizenService {
	public List<Citizen> findAll();
	public Citizen findById(int theId);
	public void save(Citizen theCitizen);
	
	public List<Object[]> getFirstName();
}
