package com.example.VaccineNation.service;

import com.example.VaccineNation.Enum.VaccineBrand;
import com.example.VaccineNation.dto.response.DoseResponse;
import com.example.VaccineNation.dto.response.PatientResponse;
import com.example.VaccineNation.exception.PatientNotFoundException;
import com.example.VaccineNation.model.Dose;
import com.example.VaccineNation.model.Patient;
import com.example.VaccineNation.repository.DoseRepository;
import com.example.VaccineNation.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoseService {
    @Autowired
    DoseRepository doseRepository;

    @Autowired
    PatientRepository patientRepository;
    public DoseResponse addDose(int patientId, VaccineBrand vaccineBrand) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(patientOptional.isEmpty()){
            throw new PatientNotFoundException("Invalid patient ID");
        }

        Patient patient = patientOptional.get();

        if(patient.isVaccinated()){
            throw new RuntimeException("Patient is already vaccinated");
        }

        patient.setVaccinated(true);

        Dose dose = new Dose();
        dose.setVaccineBrand(vaccineBrand);
        dose.setSerialNumber(String.valueOf(UUID.randomUUID()));
        dose.setPatient(patient);
        patientRepository.save(patient);

        doseRepository.save(dose);

        PatientResponse patientResponse =new PatientResponse();
        patientResponse.setName(patient.getName());
        patientResponse.setVaccination(patient.isVaccinated());
        patientResponse.setAge(patient.getAge());
        patientResponse.setEmailId(patient.getEmailId());

        DoseResponse doseResponse =new DoseResponse();
        doseResponse.setSerialNumber(dose.getSerialNumber());
        doseResponse.setVaccineBrand(dose.getVaccineBrand());
        doseResponse.setDateOfVaccination(dose.getDateOfVaccination());
        doseResponse.setPatientResponse(patientResponse);
        return doseResponse;
    }
}
