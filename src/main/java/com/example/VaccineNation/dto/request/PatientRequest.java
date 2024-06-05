package com.example.VaccineNation.dto.request;

import com.example.VaccineNation.Enum.Gender;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PatientRequest {
    private String name;
    private  int age;
    private Gender gender;
    private String emailId;
}
