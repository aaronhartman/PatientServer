package com.capstone.symptomman.patient.repository;




import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.google.common.base.Objects;
// a class for alert events
@Entity
public class PatientAlert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private long doctorId;
	
	private String patientName;
	
	private long patientId;
	
	private String alertTrigger;
	
	private long cantEatDuration;
	
	private long severePainDuration;
	
	private long severeOrModeratePainDuration;
	
	// True if alert for this patient has not been acknowledged by doctor
	private String alertStatus;
	
	
	
	
	public PatientAlert() {
		
	}
	
	
	public PatientAlert(Long id, long doctorId, String patientName,
			long patientId, String alertTrigger, long cantEatDuration,
			long severePainDuration, long severeOrModeratePainDuration,
			String alertStatus) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.patientName = patientName;
		this.patientId = patientId;
		this.alertTrigger = alertTrigger;
		this.cantEatDuration = cantEatDuration;
		this.severePainDuration = severePainDuration;
		this.severeOrModeratePainDuration = severeOrModeratePainDuration;
		this.alertStatus = alertStatus;
	}


	public String getAlertTrigger() {
		return alertTrigger;
	}

	public void setAlertTrigger(String alertTrigger) {
		this.alertTrigger = alertTrigger;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public String getAlertStatus() {
		return alertStatus;
	}


	public void setAlertStatus(String alertStatus) {
		this.alertStatus = alertStatus;
	}


	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public long getCantEatDuration() {
		return cantEatDuration;
	}

	public void setCantEatDuration(long cantEatDuration) {
		this.cantEatDuration = cantEatDuration;
	}

	public long getSeverePainDuration() {
		return severePainDuration;
	}

	public void setSeverePainDuration(long severePainDuration) {
		this.severePainDuration = severePainDuration;
	}

	public long getSevereOrModeratePainDuration() {
		return severeOrModeratePainDuration;
	}

	public void setSevereOrModeratePainDuration(long severeOrModeratePainDuration) {
		this.severeOrModeratePainDuration = severeOrModeratePainDuration;
	}
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PatientAlert) {
			PatientAlert other = (PatientAlert) obj;
			return Objects.equal(id, other.id);

		} else {
			return false;
		}
	}

}
	