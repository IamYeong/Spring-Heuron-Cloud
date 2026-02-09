package com.heuron.heuroncloud.api.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponseDto {

    private String id;

    private String name;

    private String gender;

    private String birthday;

    private Boolean hasDisease;

    private String imageUrl;

}
