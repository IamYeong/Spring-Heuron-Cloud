package com.heuron.heuroncloud.api.patient.controller;

import com.heuron.heuroncloud.api.common.dto.HttpResponseBody;
import com.heuron.heuroncloud.api.patient.dto.PatientSaveRequestDto;
import com.heuron.heuroncloud.api.patient.service.PatientApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PatientController {

    private final PatientApiService patientApiService;

    @PostMapping("/patients")
    public ResponseEntity<Object> save(
        @Valid @RequestBody PatientSaveRequestDto requestDto
    ) {
        Long responseDto = patientApiService.savePatient(requestDto);

        return HttpResponseBody.builder()
            .code(HttpStatus.CREATED)
            .message("Created")
            .response(responseDto)
            .buildAndMapToResponseEntity();
    }

}
