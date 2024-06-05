package com.example.VaccineNation.service;

import com.example.VaccineNation.Enum.Gender;
import com.example.VaccineNation.dto.request.PatientRequest;
import com.example.VaccineNation.dto.response.PatientResponse;
import com.example.VaccineNation.exception.PatientNotFoundException;
import com.example.VaccineNation.model.Patient;
import com.example.VaccineNation.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public PatientResponse addPatient(PatientRequest patientRequest) {

        // 1. request dto -> model/entity
        Patient patient = new Patient();
        patient.setVaccinated(false);
        patient.setName(patientRequest.getName());
        patient.setAge(patientRequest.getAge());
        patient.setGender(patientRequest.getGender());
        patient.setEmailId(patientRequest.getEmailId());

        // return saved patient
        Patient savepatient = patientRepository.save(patient);

        // convert  model -> response dto

        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setName(savepatient.getName());
        patientResponse.setVaccination(savepatient.isVaccinated());
        patientResponse.setAge(savepatient.getAge());
        patientResponse.setEmailId(savepatient.getEmailId());

        return patientResponse;
    }


    public PatientResponse getPatient(int id) throws PatientNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(id);
        if(patientOptional.isEmpty()){
            throw new PatientNotFoundException("Invalid patient ID");
        }
        Patient patient = patientOptional.get();
        PatientResponse patientResponse =new PatientResponse();
        patientResponse.setName(patient.getName());
        patientResponse.setVaccination(patient.isVaccinated());
        patientResponse.setAge(patient.getAge());
        patientResponse.setEmailId(patient.getEmailId());
        return patientResponse;
    }

    public List<PatientResponse> getAllPatientByGender(Gender gender) {
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponse> patientResponses = new ArrayList<>();
        for(Patient patient: patients){
            if(patient.getGender()==gender){
                PatientResponse patientResponse = new PatientResponse();
                patientResponse.setName(patient.getName());
                patientResponse.setEmailId(patient.getEmailId());
                patientResponse.setAge(patient.getAge());
                patientResponse.setVaccination(patient.isVaccinated());
                patientResponses.add(patientResponse);
            }
        }
        return patientResponses;
    }

    public List<PatientResponse> getAllVaccinatedPatientAge(int age) {
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponse> patientResponses =new ArrayList<>();
        for(Patient patient: patients){
            if(patient.getAge()>=age){
                PatientResponse patientResponse = new PatientResponse();
                patientResponse.setName(patient.getName());
                patientResponse.setEmailId(patient.getEmailId());
                patientResponse.setAge(patient.getAge());
                patientResponse.setVaccination(patient.isVaccinated());
                patientResponses.add(patientResponse);
            }
        }
        return patientResponses;
    }
}
