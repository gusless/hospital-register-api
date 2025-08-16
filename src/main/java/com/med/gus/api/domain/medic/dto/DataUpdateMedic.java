package com.med.gus.api.domain.medic.dto;

import com.med.gus.api.domain.address.dto.DataAddress;
import jakarta.validation.constraints.NotNull;

public record DataUpdateMedic(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddress endereco
) {
}
