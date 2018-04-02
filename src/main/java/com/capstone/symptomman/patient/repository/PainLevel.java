package com.capstone.symptomman.patient.repository;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class PainLevel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Long doctorId;

	private Long patientId;


	private String pain;

	private String eatPain;

	private long time;

	private ArrayList<String> medTimes;

	public ArrayList<String> getMedTimes() {
		return medTimes;
	}

	public void setMedTimes(ArrayList<String> medTimes) {
		this.medTimes = medTimes;
	}

	public PainLevel() {

	}

	public PainLevel(Long id, Long doctorId, Long patientId, String pain,
			String eatPain, long time, ArrayList<String> medTimes) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.pain = pain;
		this.eatPain = eatPain;
		this.time = time;
		this.medTimes = medTimes;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPain() {
		return pain;
	}

	public void setPain(String pain) {
		this.pain = pain;
	}

	public String getEatPain() {
		return eatPain;
	}

	public void setEatPain(String eatPain) {
		this.eatPain = eatPain;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
