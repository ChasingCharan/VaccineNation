package com.example.VaccineNation.service;

import com.example.VaccineNation.dto.request.DoctorRequest;
import com.example.VaccineNation.dto.response.DoctorResponse;
import com.example.VaccineNation.exception.DoctorNotFoundException;
import com.example.VaccineNation.model.Doctor;
import com.example.VaccineNation.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    DoctorRepository doctorRepository;


    public DoctorResponse addDoctor(DoctorRequest doctorRequest) {

        Doctor doctor = new Doctor();
        doctor.setName(doctorRequest.getName());
        doctor.setGender(doctorRequest.getGender());
        doctor.setExperience(doctorRequest.getExperience());
        doctor.setSpecialization(doctorRequest.getSpecialization());

        Doctor saveDoctor = doctorRepository.save(doctor);

        DoctorResponse doctorResponse = new DoctorResponse();
        doctorResponse.setName(saveDoctor.getName());
        doctorResponse.setExperience(saveDoctor.getExperience());
        doctorResponse.setSpecialization(saveDoctor.getSpecialization());

        return doctorResponse;
    }

    public DoctorResponse getDoctor(int id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if(doctorOptional.isEmpty()){
            throw new DoctorNotFoundException("Invalid Doctor ID");
        }

        Doctor doctor = doctorOptional.get();

        DoctorResponse doctorResponse =new DoctorResponse();
        doctorResponse.setName(doctor.getName());
        doctorResponse.setExperience(doctor.getExperience());
        doctorResponse.setSpecialization(doctor.getSpecialization());

        return doctorResponse;
    }

    public String deleteDoctor(int id) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isEmpty()){
            throw  new DoctorNotFoundException("Invalid Doctor ID");
        }
        doctorRepository.deleteById(id);
        return "Doctor has been deleted";
    }
}

