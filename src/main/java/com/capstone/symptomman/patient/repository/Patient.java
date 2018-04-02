package com.capstone.symptomman.patient.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.common.base.Objects;

@Entity
public class Patient {
	
	// For demo purposes, generated ID will be used--this would be altered for a
	// real Medical Records System API
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	
	private Long doctorId;
	
	private String name;
		
	private String username;

	private String first;

	private String last;

	private Long dateOfBirth;
	

	private ArrayList<String> medList;
	
	
	// @OneToMany(targetEntity=PainLevel.class, mappedBy="owner", fetch=FetchType.EAGER)
	@ElementCollection
	private Collection<PainLevel> painLevels;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	
	}
	public Patient() {

	}
	public Patient(String firstname, String lastname, String username, Long birthdate) {
		super();
		this.username = username;
		this.first = firstname;
		this.last = lastname;
		this.dateOfBirth = birthdate;
		this.name = "first" + " " + "last";
		
	}
	public Patient(String username, String firstname, String lastname,
			Long dateOfBirth, Long doctorId) {
		super();
		this.username = username;
		this.first = firstname;
		this.last = lastname;
		this.dateOfBirth = dateOfBirth;
		this.doctorId = doctorId;
		this.painLevels = Collections.emptyList();
		this.medList = new ArrayList<String>();
		this.name = first + " " + last;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public Long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	

	
	  public Collection<PainLevel> getPainLevels() { return painLevels; }
	  
	  public void setPainLevels(Collection<PainLevel> painLevels) {
	  this.painLevels = painLevels; }
	 
	public ArrayList<String> getMedList() {
		return medList;
	}

	public void setMedList(ArrayList<String> medList) {
		this.medList = null;
		this.medList = medList;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(username);
	}

	/**
	 * Two Patients are considered equal if they have exactly the same values for
	 * their username.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Patient) {
			Patient other = (Patient) obj;
			return Objects.equal(username, other.username);

		} else {
			return false;
		}
	}
}
