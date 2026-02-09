package com.heuron.heuroncloud.domain.patient.service;

import com.heuron.heuroncloud.domain.patient.entity.Patient;

import java.util.List;

public interface PatientDomainService {

    Patient savePatient(Patient patient);

    List<Patient> getPatients();

    void deletePatient(Patient patient);

}
