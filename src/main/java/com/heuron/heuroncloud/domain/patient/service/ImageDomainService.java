package com.heuron.heuroncloud.domain.patient.service;

import com.heuron.heuroncloud.domain.patient.entity.Patient;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageDomainService {

    void saveImage(Long patientId, MultipartFile image);

    Resource getImage(Long patientId);

    String getUrl(Patient patient);

    void deleteImages(Patient patient);

}
