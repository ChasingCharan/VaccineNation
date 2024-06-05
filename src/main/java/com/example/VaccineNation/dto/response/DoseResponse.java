package com.example.VaccineNation.dto.response;

import com.example.VaccineNation.Enum.VaccineBrand;
import com.example.VaccineNation.model.Patient;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoseResponse {
    private String serialNumber;
    private VaccineBrand vaccineBrand;
    private Date dateOfVaccination;
    PatientResponse patientResponse;
}
