package com.heuron.heuroncloud.domain.patient;

import com.heuron.heuroncloud.domain.patient.entity.Gender;
import com.heuron.heuroncloud.domain.patient.entity.Patient;
import com.heuron.heuroncloud.domain.patient.service.PatientDomainService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class PatientDomainServiceTest {

    @Autowired
    private PatientDomainService patientDomainService;

    @Test
    public void patientSaveTest() throws Exception {
        // Arrange
        Patient patient = buildPatient();

        // Action
        Patient postSavePatient = patientDomainService.savePatient(patient);

        // Assert
        Assertions.assertEquals(1L, postSavePatient.getId());
    }

    @Test
    public void emptyImageNotSelectTest() throws Exception {
        // Arrange
        Patient patient = buildPatient();
        patientDomainService.savePatient(patient);
        patientDomainService.savePatient(patient);
        patientDomainService.savePatient(patient);

        // Action
        List<Patient> patientList = patientDomainService.getPatients();

        // Assert
        Assertions.assertEquals(0, patientList.size());
    }

    private Patient buildPatient() {
        return Patient.builder()
            .name("TEST")
            .birthday(LocalDate.of(2000,1,1))
            .gender(Gender.Male)
            .hasDisease(true)
            .build();
    }

}
