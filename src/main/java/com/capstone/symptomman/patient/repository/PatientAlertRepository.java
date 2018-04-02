package com.capstone.symptomman.patient.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientAlertRepository extends CrudRepository<PatientAlert, Long> {
	public Collection<PatientAlert> findByDoctorId(long doctorId);
}
