package com.example.VaccineNation.controller;

import com.example.VaccineNation.Enum.Gender;
import com.example.VaccineNation.dto.request.PatientRequest;
import com.example.VaccineNation.dto.response.PatientResponse;
import com.example.VaccineNation.exception.PatientNotFoundException;
import com.example.VaccineNation.model.Patient;
import com.example.VaccineNation.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;
    @PostMapping("/add")
    public ResponseEntity<Object> addPatient(@RequestBody PatientRequest patientRequest){
        try {
            PatientResponse patientResponse = patientService.addPatient(patientRequest);
            return new ResponseEntity<>(patientResponse,HttpStatus.CREATED);
        }catch ( Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // getting the patient details
    @GetMapping("/get")
    public ResponseEntity<Object> getPatient(@RequestParam("id") int id) {
        try{
            PatientResponse patientResponse = patientService.getPatient(id);
            return new ResponseEntity<>(patientResponse,HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // get all the patients of a particular gender
    @GetMapping("/get/gender/{gender}")
    public ResponseEntity<List<PatientResponse>> getAllPatientByGender(@PathVariable("gender") Gender gender){
        List<PatientResponse> patientResponse =  patientService.getAllPatientByGender(gender);
        return new ResponseEntity<>(patientResponse, HttpStatus.ACCEPTED);
    }

    //get all the vaccinated patient above age 30
    @GetMapping("/get/age")
    public ResponseEntity<List<PatientResponse>> getAllVaccinatedPatientAge(@RequestParam("age") int age ){
        List<PatientResponse> patientResponses = patientService.getAllVaccinatedPatientAge(age);
        return new ResponseEntity<>(patientResponses, HttpStatus.ACCEPTED);
    }




}
