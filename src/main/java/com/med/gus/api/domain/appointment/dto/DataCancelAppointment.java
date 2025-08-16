package com.med.gus.api.domain.appointment.dto;

import com.med.gus.api.domain.appointment.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record DataCancelAppointment(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo
) {
}
