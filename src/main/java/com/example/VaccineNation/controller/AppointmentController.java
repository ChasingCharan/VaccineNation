package com.example.VaccineNation.controller;

import com.example.VaccineNation.dto.response.AppointmentResponse;
import com.example.VaccineNation.model.Appointment;
import com.example.VaccineNation.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<Object> bookAppointment(@RequestParam("pid") int patientId,
                                          @RequestParam("did") int doctorId){
        try{
            AppointmentResponse bookedAppointment = appointmentService.bookAppointment(patientId,doctorId);
            return new ResponseEntity<>(bookedAppointment,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    // get all the appointment after a particular date
    @GetMapping("/date/get")
    public ResponseEntity<List<AppointmentResponse>> getAllAppointmentsByDate(@RequestParam("date") Date date){
        List<AppointmentResponse> appointmentResponses = appointmentService.getAllAppointmentsByDate(date);
        return new ResponseEntity<>(appointmentResponses,HttpStatus.CREATED);
    }

}
