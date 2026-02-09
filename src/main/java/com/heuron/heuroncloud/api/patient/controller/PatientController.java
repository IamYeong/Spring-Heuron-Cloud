package com.heuron.heuroncloud.api.patient.controller;

import com.heuron.heuroncloud.api.common.dto.HttpResponseBody;
import com.heuron.heuroncloud.api.patient.dto.PatientResponseDto;
import com.heuron.heuroncloud.api.patient.dto.PatientSaveRequestDto;
import com.heuron.heuroncloud.api.patient.service.PatientApiService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.heuron.heuroncloud.domain.common.exception.BusinessException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<Object> savePatient(
        @Valid @RequestBody PatientSaveRequestDto requestDto
    ) throws BusinessException {
        Long responseDto = patientApiService.savePatient(requestDto);

        return HttpResponseBody.builder()
            .code(HttpStatus.CREATED)
            .message(HttpStatus.CREATED.toString())
            .response(responseDto)
            .buildAndMapToResponseEntity();
    }

    @Operation(summary = "환자목록 조회", description = "이미지까지 저장된 환자의 기본정보와 url 목록을 가져옵니다.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "조회 성공",
            content = @Content(
                array = @ArraySchema(
                    schema = @Schema(implementation = PatientResponseDto.class)))
                )
    })
    @GetMapping("/patients")
    public ResponseEntity<Object> getPatients() throws BusinessException {
        List<PatientResponseDto> responseDto = patientApiService.getPatients();

        return HttpResponseBody.builder()
            .code(HttpStatus.OK)
            .message(HttpStatus.OK.toString())
            .response(responseDto)
            .buildAndMapToResponseEntity();
    }

    @Operation(summary = "환자 삭제", description = "환자정보와 이미지를 모두 삭제합니다")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "삭제 성공",
            content = @Content(
                schema = @Schema(implementation = Boolean.class))
        )
    })
    @DeleteMapping("/patients/{patientId}")
    public ResponseEntity<Object> deletePatient(
        @PathVariable(value = "patientId") String patientId
    ) throws BusinessException {
        Boolean responseDto = patientApiService.deletePatient(patientId);

        return HttpResponseBody.builder()
            .code(HttpStatus.OK)
            .message(HttpStatus.OK.toString())
            .response(responseDto)
            .buildAndMapToResponseEntity();
    }

    @Operation(summary = "환자 이미지 저장", description = "환자 이미지 저장 후 저장결과를 반환합니다")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "저장 성공",
            content = @Content(schema = @Schema(implementation = Boolean.class)))
    })
    @PostMapping(value = "/patients/{patientId}/images",
        consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> savePatientImage(
        @PathVariable(value = "patientId") String patientId,
        @RequestParam(value = "image") MultipartFile image
    ) throws BusinessException {
        Boolean responseDto = patientApiService.saveImage(patientId, image);

        return HttpResponseBody.builder()
            .code(HttpStatus.CREATED)
            .message(HttpStatus.CREATED.toString())
            .response(responseDto)
            .buildAndMapToResponseEntity();
    }

    @Operation(summary = "환자 이미지 조회", description = "저장된 환자 이미지를 반환합니다")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "조회 성공",
            content = @Content(schema = @Schema(implementation = Resource.class)))
    })
    @GetMapping(value = "/patients/{patientId}/images")
    public ResponseEntity<Resource> getPatientImage(
        @PathVariable(value = "patientId") String patientId
    ) {
        Resource image = patientApiService.getImage(patientId);

        return ResponseEntity.ok()
            .contentType(MediaType.IMAGE_JPEG)
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
            .body(image);
    }

}
