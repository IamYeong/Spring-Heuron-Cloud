package com.heuron.heuroncloud.domain.patient.service.impl;

import com.heuron.heuroncloud.domain.common.exception.BusinessException;
import com.heuron.heuroncloud.domain.patient.entity.Patient;
import com.heuron.heuroncloud.domain.patient.repository.PatientJpaRepository;
import com.heuron.heuroncloud.domain.patient.service.PatientDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientDomainServiceImpl implements PatientDomainService {

    private final PatientJpaRepository patientJpaRepository;

    @Override
    public Patient savePatient(Patient patient) {
        return patientJpaRepository.save(patient);
    }

    @Override
    public Patient getPatient(Long id) throws BusinessException {
        return patientJpaRepository.findById(id)
            .orElseThrow(BusinessException::new);
    }
}
