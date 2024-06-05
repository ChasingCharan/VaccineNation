package com.example.VaccineNation.model;

import com.example.VaccineNation.Enum.VaccineBrand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Dose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String serialNumber;
    @Enumerated(value = EnumType.STRING)
    private VaccineBrand vaccineBrand;
    @CreationTimestamp
    private Date dateOfVaccination;

    @OneToOne
    @JoinColumn
    Patient patient;

}
