package com.capstone.symptomman.patient.repository;


import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface PatientRepository extends CrudRepository<Patient, Long>{

	public Patient findByUsername(String username);
	
	public Patient findByName(String name);
	
	public Collection<Patient> findByDoctorId(Long doctorId);
	
}
