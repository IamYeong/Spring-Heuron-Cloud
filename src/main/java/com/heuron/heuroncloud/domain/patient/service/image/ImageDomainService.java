package com.heuron.heuroncloud.domain.patient.service.image;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImageDomainService {

    void saveImage(Long patientId, MultipartFile image);

    Resource getImage(Long patientId);

}
