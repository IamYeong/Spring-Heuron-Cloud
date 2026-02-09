package com.heuron.heuroncloud.domain.patient.service.impl;

import com.heuron.heuroncloud.domain.common.exception.BusinessException;
import com.heuron.heuroncloud.domain.patient.entity.Patient;
import com.heuron.heuroncloud.domain.patient.repository.PatientRepository;
import com.heuron.heuroncloud.domain.patient.service.PatientDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

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
    public Patient getPatient(Long id) {
        return patientRepository.findById(id)
            .orElseThrow(() -> new BusinessException(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.name()));
    }
}
