package com.heuron.heuroncloud.api.patient.service;

import com.heuron.heuroncloud.api.patient.dto.PatientSaveRequestDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface PatientApiService {

    Long savePatient(PatientSaveRequestDto dto);

    Boolean saveImage(String patientId, MultipartFile image);

    Resource getImage(String patientId);

}
