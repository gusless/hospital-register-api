package com.med.gus.api.domain.medic;

import com.med.gus.api.domain.address.DataAddress;
import jakarta.validation.constraints.NotNull;

public record DataUpdateMedic(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddress endereco
) {
}
