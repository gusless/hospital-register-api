package com.med.gus.api.medic;

import com.med.gus.api.address.DataAddress;
import com.med.gus.api.address.Endereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DataUpdateMedic(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DataAddress endereco
) {
}
