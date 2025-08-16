package com.med.gus.api.domain.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataAddress(
        @NotBlank(message = "{logradouro.obrigatorio}")
        String logradouro,
        @NotBlank(message = "{bairro.obrigatorio}")
        String bairro,
        @NotBlank(message = "{cep.obrigatorio}")
        @Pattern(regexp = "\\d{8}", message = "{cep.invalido}")
        String cep,
        @NotBlank(message = "{cidade.obrigatorio}")
        String cidade,
        @NotBlank(message = "{uf.obrigatorio}")
        String uf,
        String numero,
        String complemento
) {}
