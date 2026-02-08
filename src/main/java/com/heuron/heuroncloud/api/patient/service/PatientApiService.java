package com.heuron.heuroncloud.api.patient.service;

import com.heuron.heuroncloud.api.patient.dto.PatientSaveRequestDto;

public interface PatientApiService {

    Long savePatient(PatientSaveRequestDto dto);

}
