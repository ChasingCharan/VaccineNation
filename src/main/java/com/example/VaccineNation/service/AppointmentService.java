package com.example.VaccineNation.service;

import com.example.VaccineNation.Enum.AppointmentStatus;
import com.example.VaccineNation.dto.response.AppointmentResponse;
import com.example.VaccineNation.dto.response.PatientResponse;
import com.example.VaccineNation.exception.DoctorNotFoundException;
import com.example.VaccineNation.exception.PatientNotFoundException;
import com.example.VaccineNation.model.Appointment;
import com.example.VaccineNation.model.Doctor;
import com.example.VaccineNation.model.Patient;
import com.example.VaccineNation.repository.AppointmentRepository;
import com.example.VaccineNation.repository.DoctorRepository;
import com.example.VaccineNation.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    AppointmentRepository appointmentRepository;
    public AppointmentResponse bookAppointment(int patientId, int doctorId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(patientOptional.isEmpty()){
            throw new PatientNotFoundException("Invalid Patient ID");
        }
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFoundException("Invalid Doctor ID");
        }
        Patient patient = patientOptional.get();
        Doctor doctor = doctorOptional.get();

        //book the appointment
        Appointment appointment =new Appointment();
        appointment.setAppointmentId(String.valueOf(UUID.randomUUID()));
        appointment.setStatus(AppointmentStatus.BOOKED);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        Appointment saveAppointment = appointmentRepository.save(appointment);

        // model to response dto
        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setAppointmentId(saveAppointment.getAppointmentId());
        appointmentResponse.setStatus(saveAppointment.getStatus());
        appointmentResponse.setDateOfAppointment(saveAppointment.getDateOfAppointment());
        appointmentResponse.setDoctorName(saveAppointment.getDoctor().getName());

        Patient savePatient = saveAppointment.getPatient();
        PatientResponse patientResponse = new PatientResponse();
        patientResponse.setName(savePatient.getName());
        patientResponse.setEmailId(savePatient.getEmailId());
        patientResponse.setAge(savePatient.getAge());
        patientResponse.setVaccination(savePatient.isVaccinated());

        appointmentResponse.setPatientResponse(patientResponse);

        return appointmentResponse;
    }

    public List<AppointmentResponse> getAllAppointmentsByDate(Date date) {
        List<Appointment> appointments =appointmentRepository.findAll();
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        for(Appointment appointment : appointments){
            if (appointment.getDateOfAppointment().equals(date)){
                AppointmentResponse appointmentResponse = new AppointmentResponse();
                appointmentResponse.setAppointmentId(appointment.getAppointmentId());
                appointmentResponse.setStatus(appointment.getStatus());
                appointmentResponse.setDateOfAppointment(appointment.getDateOfAppointment());
                appointmentResponse.setDoctorName(appointment.getDoctor().getName());

                Patient patient = appointment.getPatient();
                PatientResponse patientResponse = new PatientResponse();
                patientResponse.setName(patient.getName());
                patientResponse.setEmailId(patient.getEmailId());
                patientResponse.setAge(patient.getAge());
                patientResponse.setVaccination(patient.isVaccinated());
                appointmentResponse.setPatientResponse(patientResponse);

                appointmentResponses.add(appointmentResponse);
            }
        }
        return appointmentResponses;
    }
}
