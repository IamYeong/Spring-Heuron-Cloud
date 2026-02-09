package com.heuron.heuroncloud.api.patient.service.impl;

import com.heuron.heuroncloud.api.patient.dto.PatientResponseDto;
import com.heuron.heuroncloud.api.patient.dto.PatientSaveRequestDto;
import com.heuron.heuroncloud.api.patient.service.PatientApiService;
import com.heuron.heuroncloud.domain.patient.entity.Patient;
import com.heuron.heuroncloud.domain.patient.service.PatientDomainService;
import com.heuron.heuroncloud.domain.patient.service.ImageDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class PatientApiServiceImpl implements PatientApiService {

    private final PatientDomainService patientDomainService;

    private final ImageDomainService imageDomainService;

    @Value("${image.url}")
    private String imageUrl;

    @Override
    @Transactional
    public Long savePatient(PatientSaveRequestDto dto) {
        Patient patient = dto.toEntity();
        return patientDomainService.savePatient(patient).getId();
    }

    @Override
    public List<PatientResponseDto> getPatients() {
        return patientDomainService.getPatients().stream()
            .map(this::convertToDto)
            .toList();
    }

    private PatientResponseDto convertToDto(Patient patient) {
        String patientId = Long.toString(patient.getId());

        String url = "http://" + imageUrl + "/api/patients/" + patientId + "/images";

        return PatientResponseDto.builder()
            .id(patientId)
            .name(patient.getName())
            .gender(patient.getGender().name())
            .hasDisease(patient.isHasDisease())
            .birthday(patient.getBirthday().toString())
            .imageUrl(url)
            .build();
    }

    @Override
    @Transactional
    public Boolean saveImage(String patientId, MultipartFile image) {
        imageDomainService.saveImage(Long.parseLong(patientId), image);
        return true;
    }

    @Override
    public Resource getImage(String patientId) {
        return imageDomainService.getImage(Long.parseLong(patientId));
    }
}
