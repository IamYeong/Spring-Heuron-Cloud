package com.heuron.heuroncloud.domain.patient.repository;

import com.heuron.heuroncloud.domain.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientJpaRepository extends JpaRepository<Patient, Long> {
}
