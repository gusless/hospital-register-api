package com.med.gus.api.patient;

import com.med.gus.api.address.DataAddress;
import com.med.gus.api.address.Endereco;
import com.med.gus.api.medic.DataDetailMedic;
import org.springframework.http.ProblemDetail;

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
