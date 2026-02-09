package com.heuron.heuroncloud.domain.patient.service.impl;

import com.heuron.heuroncloud.domain.common.exception.BusinessException;
import com.heuron.heuroncloud.domain.patient.entity.Patient;
import com.heuron.heuroncloud.domain.patient.repository.PatientRepository;
import com.heuron.heuroncloud.domain.patient.service.ImageDomainService;
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
public class ImageDomainServiceImpl implements ImageDomainService {

    private final PatientRepository patientRepository;

    @Value("${image.path}")
    private String imageBasePath;

    @Override
    @Transactional
    public void saveImage(Long patientId, MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.value(), "Empty Image");
        }

        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new BusinessException(HttpStatus.BAD_REQUEST.value(), "Patient not found"));

        String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        String extension = ".jpeg";
        validateImageExtension(extension);
        deleteExistingImageIfExists(patient);
        String imageId = UUID.randomUUID().toString();
        String fileName = imageId + extension;

        patient.setImageId(imageId);

        Path baseDir = Paths.get(imageBasePath).toAbsolutePath().normalize();
        Path targetPath = baseDir
            .resolve(Long.toString(patientId))
            .resolve(today)
            .toAbsolutePath()
            .normalize();

        try {
            Files.createDirectories(targetPath);
            Path targetFilePath = targetPath.resolve(fileName);
            image.transferTo(targetFilePath.toFile());
        } catch (IOException e) {
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to save image file");
        }
    }

    private void deleteExistingImageIfExists(Patient patient) {
        String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        Path fullPath = Paths.get(imageBasePath)
            .resolve(Long.toString(patient.getId()))
            .resolve(today)
            .normalize();

        if (!Files.exists(fullPath)) {
            return;
        }

        try {
            Files.walk(fullPath)
                .sorted(Comparator.reverseOrder())
                .forEach(this::deleteImage);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete existing image", e);
        }
    }

    private void deleteImage(Path path) {
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to delete existing image", e);
        }
    }

    private void validateImageExtension(String ext) {
        if (!List.of(".jpg", ".jpeg").contains(ext)) {
            throw new BusinessException(HttpStatus.BAD_REQUEST.value(), "Unsupported image type");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Resource getImage(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new BusinessException(HttpStatus.BAD_REQUEST.value(), "Patient not found"));

        String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

        if (patient.getImageId() == null) {
            throw new BusinessException(HttpStatus.NO_CONTENT.value(), "Empty Image");
        }

        Path imagePath = Paths.get(imageBasePath)
            .resolve(Long.toString(patientId))
            .resolve(today)
            .resolve(patient.getImageId().concat(".jpeg"))
            .normalize();

        System.out.println(patient.getId());
        System.out.println(today);
        System.out.println(patient.getImageId());
        System.out.println(imagePath.toString());

        if (!Files.exists(imagePath)) {
            throw new BusinessException(
                HttpStatus.NOT_FOUND.value(), "Image file not found"
            );
        }

        try {
            return new UrlResource(imagePath.toUri());
        } catch (IOException e) {
            throw new BusinessException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Failed to load image file"
            );
        }
    }
}
