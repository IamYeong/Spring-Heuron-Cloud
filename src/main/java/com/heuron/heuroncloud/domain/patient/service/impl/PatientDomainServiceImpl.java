package com.heuron.heuroncloud.domain.patient.service.impl;

import com.heuron.heuroncloud.domain.patient.entity.Patient;
import com.heuron.heuroncloud.domain.patient.repository.PatientRepository;
import com.heuron.heuroncloud.domain.patient.service.PatientDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Patient> getPatients() {
        return patientRepository.findAll()
            .stream().filter(p -> p.getImageId() != null)
            .collect(Collectors.toList());
    }
}
