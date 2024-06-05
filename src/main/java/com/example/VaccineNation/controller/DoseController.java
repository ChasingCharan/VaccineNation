package com.example.VaccineNation.controller;

import com.example.VaccineNation.Enum.VaccineBrand;
import com.example.VaccineNation.dto.response.DoseResponse;
import com.example.VaccineNation.model.Dose;
import com.example.VaccineNation.service.DoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dose")
public class DoseController {

    @Autowired
    DoseService doseService;
    @PostMapping("/add")
    public ResponseEntity<Object> addDose(@RequestParam("id") int patientId,
                                          @RequestParam("brand")VaccineBrand vaccineBrand){
        try{
            DoseResponse doseResponse = doseService.addDose(patientId,vaccineBrand);
            return new ResponseEntity<>(doseResponse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
