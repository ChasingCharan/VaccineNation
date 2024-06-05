package com.example.VaccineNation.controller;

import com.example.VaccineNation.dto.request.DoctorRequest;
import com.example.VaccineNation.dto.response.DoctorResponse;
import com.example.VaccineNation.model.Doctor;
import com.example.VaccineNation.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<Object> addDoctor(@RequestBody DoctorRequest doctorRequest){
        try {
            DoctorResponse doctorResponse = doctorService.addDoctor(doctorRequest);
            return new ResponseEntity<>(doctorResponse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getDoctor(@RequestParam("id") int id){
        try {
            DoctorResponse doctorResponse = doctorService.getDoctor(id);
            return new ResponseEntity<>(doctorResponse, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteDoctor(@RequestParam("id") int id){
        try {
            String deleteDoctor = doctorService.deleteDoctor(id);
            return new ResponseEntity<>(deleteDoctor, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
