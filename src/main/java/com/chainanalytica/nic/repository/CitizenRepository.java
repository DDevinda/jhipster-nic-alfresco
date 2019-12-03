package com.chainanalytica.nic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.chainanalytica.nic.service.dto.Citizen;




public interface CitizenRepository extends JpaRepository<Citizen, Integer> {
	
    @Query(value="select firstName,lastName from Citizen")
    List<Object[]> getFirstNames();
	
}
