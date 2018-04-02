package com.capstone.symptomman.patient.repository;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.Objects;

@Entity
public class Doctor {

	// For demo purposes, generated ID will be used--this would be altered for a
	// real Medical Records System API
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long IdNumber;
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	
	@ElementCollection
	private Collection<PatientAlert> patientAlerts;
	
	public Doctor() {
		
	}
	
	
	
	public Doctor(Long idNumber, String username, String firstName,
			String lastName, Collection<PatientAlert> patientAlerts) {
		super();
		IdNumber = idNumber;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.patientAlerts = patientAlerts;
	}


	public Long getIdNumber() {
		return IdNumber;
	}
	
	public void setIdNumber(Long idNumber) {
		IdNumber = idNumber;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Collection<PatientAlert> getPatientAlerts() {
		return patientAlerts;
	}

	public void setPatientAlerts(Collection<PatientAlert> patientAlerts) {
		this.patientAlerts = patientAlerts;
	}
	@Override
	public int hashCode() {
		return Objects.hashCode(username);
	}

	public void removeAlert(PatientAlert alert) {
		Collection<PatientAlert> list = getPatientAlerts();
		list.remove(alert);
		setPatientAlerts(list);
	}
	
	public void addAlert(PatientAlert alert) {
		Collection<PatientAlert> list = getPatientAlerts();
		list.add(alert);
		setPatientAlerts(list);
	}
	/**
	 * Two Doctors are considered equal if they have exactly the same values for
	 * their username.
	 * 
	 */
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Doctor) {
			Doctor other = (Doctor) obj;
			return Objects.equal(username, other.username);

		} else {
			return false;
		}
	}

	
}
