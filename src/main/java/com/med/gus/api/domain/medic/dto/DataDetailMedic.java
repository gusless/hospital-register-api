package com.med.gus.api.domain.medic.dto;

import com.med.gus.api.domain.address.Endereco;
import com.med.gus.api.domain.medic.Especialidade;
import com.med.gus.api.domain.medic.Medic;

public record DataDetailMedic(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public DataDetailMedic(Medic medic){
        this(
                medic.getId(),
                medic.getNome(),
                medic.getEmail(),
                medic.getCrm(),
                medic.getTelefone(),
                medic.getEspecialidade(),
                medic.getAddress()
        );
    }
}
