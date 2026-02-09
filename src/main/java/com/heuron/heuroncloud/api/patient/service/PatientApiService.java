package com.heuron.heuroncloud.api.patient.service;

import com.heuron.heuroncloud.api.patient.dto.PatientResponseDto;
import com.heuron.heuroncloud.api.patient.dto.PatientSaveRequestDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PatientApiService {

    Long savePatient(PatientSaveRequestDto dto);

    List<PatientResponseDto> getPatients();

    Boolean deletePatient(String patientId);

    Boolean saveImage(String patientId, MultipartFile image);

    Resource getImage(String patientId);

}
