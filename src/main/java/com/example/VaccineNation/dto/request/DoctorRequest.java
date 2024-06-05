package com.example.VaccineNation.dto.request;

import com.example.VaccineNation.Enum.Gender;
import com.example.VaccineNation.Enum.Specialization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DoctorRequest {
    private String name;
    private int experience;
    private Gender gender;
    private Specialization specialization;
}
