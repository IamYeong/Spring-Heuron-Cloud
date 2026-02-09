package com.heuron.heuroncloud.domain.patient.service;

import com.heuron.heuroncloud.domain.common.exception.BusinessException;
import com.heuron.heuroncloud.domain.patient.entity.Patient;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface PatientDomainService {

    Patient savePatient(Patient patient);

    Patient getPatient(Long id);

    void saveImage(Long patientId, MultipartFile image);

    Resource getImage(Long patientId);

}
