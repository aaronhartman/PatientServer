/*
 * 
 * Copyright 2014 Jules White
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.capstone.symptomman.patient;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capstone.symptomman.Role;
import com.capstone.symptomman.patient.repository.Config;
import com.capstone.symptomman.patient.repository.Doctor;
import com.capstone.symptomman.patient.repository.DoctorRepository;
import com.capstone.symptomman.patient.repository.PainLevel;
import com.capstone.symptomman.patient.repository.PainLevelRepository;
import com.capstone.symptomman.patient.repository.Patient;
import com.capstone.symptomman.patient.repository.PatientAlert;
import com.capstone.symptomman.patient.repository.PatientAlertRepository;
import com.capstone.symptomman.patient.repository.PatientRepository;

@Controller
public class UserController {
	DateFormat df = new SimpleDateFormat("MMM d, yyyy");
	
	@Autowired
	private DoctorRepository docs;

	@Autowired
	private PatientRepository patients;
	
	@Autowired
	private PainLevelRepository checkIns;
	
	@Autowired
	private PatientAlertRepository alerts;
	
	
	
	@RequestMapping(value = Config.USER_SVC_PATH, method = RequestMethod.GET)
	public @ResponseBody
	Role getUserRole(HttpServletResponse response) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		if (userDetails.getAuthorities().toString().contains("DOCTOR")) {
			return Role.DOCTOR;
		} else {
			return Role.PATIENT;
		}
	}
	
	@RequestMapping(value = Config.DOCTOR_PATH + "/id", method = RequestMethod.GET)
	public @ResponseBody Long getDoctorId(Principal p) {
		return docs.findByUsername(p.getName()).getIdNumber();
	}
	
	@RequestMapping(value = Config.DOCTOR_SEARCH_PATH, method = RequestMethod.GET)
	public @ResponseBody Doctor getDoctor(Principal p) {
		return docs.findByUsername(p.getName());
	}
	
	@RequestMapping(value = Config.PATIENT_NAME_SEARCH_PATH, method = RequestMethod.GET)
	public @ResponseBody Patient findByName(@RequestParam(Config.PATIENT_NAME_SEARCH_PARAM) String name) {
		return patients.findByName(name);
	}
	@RequestMapping(value = Config.PATIENT_PATH + "/id", method = RequestMethod.GET)
	public @ResponseBody Long getPatientId(Principal p) {
		return patients.findByUsername(p.getName()).getId();
	}
	
	
	
	@RequestMapping(value = Config.PATIENT_SEARCH_PATH, method = RequestMethod.GET)
	public @ResponseBody
	Collection<Patient> findByDocId(@RequestParam(Config.FIND_BY_DOCTOR_PARAM) Long doctorId) {
		return patients.findByDoctorId(doctorId);
	}
	
	@RequestMapping(value = Config.PATIENT_PATH, method = RequestMethod.GET)
	public @ResponseBody
	Boolean exists(Principal p, HttpServletResponse response) {

		if (patients.findByUsername(p.getName()) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = Config.PATIENT_PATH + "/current", method = RequestMethod.GET)
	public @ResponseBody
	Patient getCurrent(Principal p, HttpServletResponse response) {

		return patients.findByUsername(p.getName());
	}
	
	@RequestMapping(value = Config.DOCTOR_PATH, method = RequestMethod.GET)
	public @ResponseBody
	Boolean docExists(Principal p, HttpServletResponse response) {

		if (docs.findByUsername(p.getName()) != null) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = Config.PATIENT_PATH, method = RequestMethod.POST)
	public @ResponseBody
	void addPatient(@RequestBody Patient patient, Principal p) {
		// Patients may only register the username that they are signed in with
		// (Production App would user additional validation) 
		patient.setUsername(p.getName());
		patients.save(patient);
	}
	
	
	
	@RequestMapping(value = Config.DOCTOR_PATH, method = RequestMethod.POST)
	public @ResponseBody void addDoctor(@RequestBody Doctor doctor, Principal p) {
		
		// Server will only save with the username of the logged-in user.
		doctor.setUsername(p.getName());
		
		docs.save(doctor);
	}
	
	@RequestMapping(value = Config.PATIENT_PATH + "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Patient getPatient(@PathVariable long id, HttpServletResponse response) {
		return patients.findOne(id);
	}
	
	@RequestMapping(value = Config.PATIENT_PATH + "/{id}/meds", method = RequestMethod.GET)
	public @ResponseBody ArrayList<String> getMeds(@PathVariable long id, HttpServletResponse response) {
		return patients.findOne(id).getMedList();
	}
	
	@RequestMapping(value = Config.PATIENT_PATH + "/{id}/meds", method = RequestMethod.POST)
	public @ResponseBody ArrayList<String> setMeds(@PathVariable long id, @RequestBody ArrayList<String> newList) {
		Patient p = patients.findOne(id);
		p.setMedList(newList);
		patients.save(p);
		return patients.findOne(id).getMedList();
		
		
	}
	
	@RequestMapping(value = Config.PATIENT_PATH + "/checkin", method = RequestMethod.POST)
	public @ResponseBody Collection<PainLevel> checkIn(@RequestBody PainLevel painLevel, Principal p) {
		Patient patient = patients.findByUsername(p.getName());
		Collection<PainLevel> list = patient.getPainLevels();
		Long patientId = patient.getId();
		Long doctorId = patient.getDoctorId();
		painLevel.setDoctorId(doctorId);
		painLevel.setPatientId(patientId);
		Long newId = checkIns.save(painLevel).getId();
		list.add(checkIns.findOne(newId));
		patient.setPainLevels(list);
		patients.save(patient);
		return patients.findByUsername(p.getName()).getPainLevels();
	}
	
	@RequestMapping(value = Config.PATIENT_PATH + "/checkin", method = RequestMethod.GET)
	public @ResponseBody Collection<PainLevel> getCheckinsByPatient(@PathVariable long patientId) {
		return checkIns.findByPatientId(patientId);
	}
	@RequestMapping(value = Config.DOCTOR_PATH + "/{id}/checkins", method = RequestMethod.GET) 
	public @ResponseBody Collection<PainLevel> getCheckins(@PathVariable long doctorId) {
		return checkIns.findByDoctorId(doctorId);
	}
	
	@RequestMapping(value = Config.DOCTOR_PATH + "/alerts", method = RequestMethod.GET)
	public @ResponseBody Collection<PatientAlert> getAlerts(Principal p) {
		
		return docs.findByUsername(p.getName()).getPatientAlerts();
	}
	
	@RequestMapping(value = Config.DOCTOR_PATH + "/alerts", method = RequestMethod.POST)
	public @ResponseBody void addAlert(@RequestBody PatientAlert alert) {
		long alertId = alerts.save(alert).getId();
		long docId = alert.getDoctorId();
		Doctor owner = docs.findOne(docId);
		Collection<PatientAlert> docsList = owner.getPatientAlerts();
		
		if (docsList == null) {
			Collection<PatientAlert> list = new ArrayList<PatientAlert>();
			list.add(alerts.findOne(alertId));
			owner.setPatientAlerts(list);
			docs.save(owner);
		} else {
			docsList.add(alerts.findOne(alertId));
			owner.setPatientAlerts(docsList);
			docs.save(owner);
		}
		
		
	}
	@RequestMapping(value = Config.DOCTOR_PATH + "/alerts/update", method = RequestMethod.POST)
	public @ResponseBody void markAlert(@RequestBody long alertId, Principal p, HttpServletResponse response) {
		PatientAlert alert = alerts.findOne(alertId);
		Doctor doctor = docs.findByUsername(p.getName());
		doctor.removeAlert(alert);
		alert.setAlertStatus("HANDLED");
		doctor.addAlert(alert);
		alerts.save(alert);
		docs.save(doctor);
	}	
	
}
