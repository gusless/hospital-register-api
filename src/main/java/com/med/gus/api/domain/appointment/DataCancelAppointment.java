package com.med.gus.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record DataCancelAppointment(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo
) {
}
