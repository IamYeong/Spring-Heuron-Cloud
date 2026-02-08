package com.heuron.heuroncloud.domain.patient.service.impl;

import com.heuron.heuroncloud.domain.common.exception.BusinessException;
import com.heuron.heuroncloud.domain.patient.entity.Patient;
import com.heuron.heuroncloud.domain.patient.repository.PatientRepository;
import com.heuron.heuroncloud.domain.patient.service.PatientDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
class PatientDomainServiceImpl implements PatientDomainService {

    private final PatientRepository patientRepository;

    @Value("${image.path}")
    private String imageBasePath;

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

    @Override
    public void saveImage(Long patientId, MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.value(), "Empty Image");
        }

        patientRepository.findById(patientId)
            .orElseThrow(() -> new BusinessException(HttpStatus.BAD_REQUEST.value(), "Patient not found"));

        String extension = extractExtension(image.getOriginalFilename());
        validateImageExtension(extension);

        String fileName = "patient_" + patientId + "_" + UUID.randomUUID() + extension;

        Path baseDir = Paths.get(imageBasePath).toAbsolutePath().normalize();
        Path targetPath = baseDir.resolve(fileName);

        try {
            Files.createDirectories(baseDir);
            image.transferTo(targetPath.toFile());
        } catch (IOException e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to save image file");
        }
    }

    private String extractExtension(String filename) {
        if (filename == null || !filename.contains(".")) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.value(), "Invalid file name");
        }
        return filename.substring(filename.lastIndexOf(".")).toLowerCase();
    }

    private void validateImageExtension(String ext) {
        if (!List.of(".jpg", ".jpeg").contains(ext)) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.value(), "Unsupported image type");
        }
    }
}
