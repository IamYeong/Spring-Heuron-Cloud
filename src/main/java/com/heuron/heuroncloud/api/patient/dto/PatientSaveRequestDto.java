package com.heuron.heuroncloud.api.patient.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientSaveRequestDto {

    @NotNull
    @NotEmpty
    private String name;

    @Pattern(regexp = "^(19[0-9][0-9]|20[0-9][0-9])-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$")
    private String birthday;

    @Pattern(regexp = "^[mfMF]$")
    private String gender;

    @NotNull
    private Boolean hasDisease;

}
