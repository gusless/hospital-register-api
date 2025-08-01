package com.med.gus.api.domain.patient;

import com.med.gus.api.domain.address.DataAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DataRegisterPatient(
        @NotBlank(message = "{nome.obrigatorio}")
        String nome,
        @NotBlank(message = "{email.obrigatorio}")
        @Email(message = "{email.invalido}")
        String email,
        @NotBlank(message = "{telefone.obrigatorio}")
        String telefone,
        @NotBlank(message = "{cpf.obrigatorio}")
        @Pattern(regexp = "\\d{11}", message = "{cpf.obrigatorio}")
        String cpf,
        @NotNull(message = "{endereco.obrigatorio}")
        @Valid
        DataAddress endereco
) {}
