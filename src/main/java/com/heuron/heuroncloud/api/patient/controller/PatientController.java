package com.heuron.heuroncloud.api.patient.controller;

import com.heuron.heuroncloud.api.common.dto.HttpResponseBody;
import com.heuron.heuroncloud.api.patient.dto.PatientSaveRequestDto;
import com.heuron.heuroncloud.api.patient.service.PatientApiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.heuron.heuroncloud.domain.common.exception.BusinessException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "환자 관련 API", description = "환자정보와 직접 관련된 API")
public class PatientController {

    private final PatientApiService patientApiService;

    @Operation(summary = "환자정보 저장", description = "환자정보 저장 후 ID 를 반환합니다(client 편의목적)")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "저장 성공",
            content = @Content(schema = @Schema(implementation = Long.class)))
    })
    @PostMapping("/patients")
    public ResponseEntity<Object> save(
        @Valid @RequestBody PatientSaveRequestDto requestDto
    ) throws BusinessException {
        Long responseDto = patientApiService.savePatient(requestDto);

        return HttpResponseBody.builder()
            .code(HttpStatus.CREATED)
            .message(HttpStatus.CREATED.toString())
            .response(responseDto)
            .buildAndMapToResponseEntity();
    }

}
