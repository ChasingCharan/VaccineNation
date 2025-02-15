package com.example.VaccineNation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PatientResponse {
    private String name;
    private boolean vaccination;
    private int age;
    private String emailId;
}
