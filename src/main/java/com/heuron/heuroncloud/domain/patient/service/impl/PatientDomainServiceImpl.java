package com.heuron.heuroncloud.domain.patient.service.impl;

import com.heuron.heuroncloud.domain.common.exception.BusinessException;
import com.heuron.heuroncloud.domain.patient.entity.Patient;
import com.heuron.heuroncloud.domain.patient.repository.PatientRepository;
import com.heuron.heuroncloud.domain.patient.service.PatientDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class PatientDomainServiceImpl implements PatientDomainService {

    private final PatientRepository patientRepository;

    @Override
    @Transactional
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(Long id) throws BusinessException {
        return patientRepository.findById(id)
            .orElseThrow(BusinessException::new);
    }
}
