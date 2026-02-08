package com.heuron.heuroncloud.api.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientSaveRequestDto {

    private String name;

    private String birthday;

    private String gender;

    private Boolean hasDisease;

}
