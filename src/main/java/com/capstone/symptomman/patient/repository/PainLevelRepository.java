package com.capstone.symptomman.patient.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PainLevelRepository extends CrudRepository<PainLevel, Long> {

	public Collection<PainLevel> findByDoctorId(Long doctorId);
	
	public Collection<PainLevel> findByPatientId(Long patientId);
}
