package com.heuron.heuroncloud.api.patient.service.impl;

import com.heuron.heuroncloud.api.patient.dto.PatientSaveRequestDto;
import com.heuron.heuroncloud.api.patient.service.PatientApiService;
import com.heuron.heuroncloud.domain.patient.entity.Patient;
import com.heuron.heuroncloud.domain.patient.service.PatientDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class PatientApiServiceImpl implements PatientApiService {

    private final PatientDomainService patientDomainService;

    @Override
    @Transactional
    public Long savePatient(PatientSaveRequestDto dto) {
        Patient patient = dto.toEntity();
        return patientDomainService.savePatient(patient).getId();
    }
}
