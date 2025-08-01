package com.med.gus.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.med.gus.api.domain.medic.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataScheduleAppointment(
        Long idMedic,

        @NotNull
        Long idPatient,

        @NotNull
        @Future
        @JsonAlias("data")
        LocalDateTime data,

        Especialidade especialidade
) {
}
