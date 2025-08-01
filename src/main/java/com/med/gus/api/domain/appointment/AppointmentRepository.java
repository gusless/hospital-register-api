package com.med.gus.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedic, LocalDateTime data);

    Boolean existsByPatientIdAndDataBetween(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);
}
