package com.heuron.heuroncloud.domain.patient.service;

import com.heuron.heuroncloud.domain.common.exception.BusinessException;
import com.heuron.heuroncloud.domain.patient.entity.Patient;

public interface PatientDomainService {

    Patient savePatient(Patient patient);

    Patient getPatient(Long id);

}
