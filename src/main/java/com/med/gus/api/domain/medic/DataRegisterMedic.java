package com.med.gus.api.domain.medic;

import com.med.gus.api.domain.address.DataAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DataRegisterMedic(
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{email.obrigatorio}")
        @Email
        String email,
        @NotBlank(message = "{telefone.obrigatorio}")
        String telefone,
        @NotBlank(message = "{crm.obrigatorio}")
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull(message = "{especialidade.obrigatorio}")
        Especialidade especialidade,
        @NotNull(message = "{endereco.obrigatorio}")
        @Valid
        DataAddress endereco
) {}
