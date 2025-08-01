package com.med.gus.api.domain.appointment;

import java.time.LocalDateTime;

public record DataDetailScheduleAppointment(
        Long id,
        Long idMedic,
        Long idPatient,
        LocalDateTime data
) {
    public DataDetailScheduleAppointment(Appointment appointment) {
        this(
                appointment.getId(),
                appointment.getMedico().getId(),
                appointment.getPatient().getId(),
                appointment.getData());
    }
}
