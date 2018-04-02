package com.capstone.symptomman.patient.repository;

// Synch Client data from com.capstone.symptomman.client.UserSvcApi

public class Config {
	public static final String SERVER = "https://localhost:8443";
	
	public static final String PASSWORD_PARAMETER = "password";

	public static final String USERNAME_PARAMETER = "username";
	
	public static final String PATIENT_PATH = "/patient";
	
	public static final String PATIENT_NAME_SEARCH_PARAM = "name";
	
	public static final String PATIENT_NAME_SEARCH_PATH = PATIENT_PATH + "/search/findByName";
	
	public static final String TOKEN_PATH = "/oauth/token";
	
	public static final String USER_SVC_PATH = "/user";
	
	
	public static final String DOCTOR_PATH = "/doctor";
	
	public static final String PATIENT_SEARCH_PATH = 
			PATIENT_PATH + "/search/findByDoctorId";
	
	public static final String DOCTOR_SEARCH_PATH = DOCTOR_PATH + "/search";
	
	public static final String FIND_BY_DOCTOR_PARAM = "doctorId";
	
	public static final String FIND_BY_PATIENT_ID_PARAM = "patientId";
	
}
