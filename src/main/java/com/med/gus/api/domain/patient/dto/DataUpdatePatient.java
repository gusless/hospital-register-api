package com.med.gus.api.domain.patient.dto;

import com.med.gus.api.domain.address.dto.DataAddress;
import jakarta.validation.constraints.NotNull;

public record DataUpdatePatient(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddress endereco
) {
}
