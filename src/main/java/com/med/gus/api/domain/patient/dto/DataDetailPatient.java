package com.med.gus.api.domain.patient.dto;

import com.med.gus.api.domain.address.Endereco;
import com.med.gus.api.domain.patient.Patient;

public record DataDetailPatient(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
    public DataDetailPatient(Patient patient){
        this(
                patient.getId(),
                patient.getNome(),
                patient.getEmail(),
                patient.getTelefone(),
                patient.getCpf(),
                patient.getEndereco()
        );
    }
}
