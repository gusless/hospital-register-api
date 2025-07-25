package com.med.gus.api.patient;

import com.med.gus.api.address.DataAddress;
import jakarta.validation.constraints.NotNull;

public record DataUpdatePatient(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddress endereco
) {
}
