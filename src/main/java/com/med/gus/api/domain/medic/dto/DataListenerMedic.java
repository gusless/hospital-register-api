package com.med.gus.api.domain.medic.dto;

import com.med.gus.api.domain.medic.Especialidade;
import com.med.gus.api.domain.medic.Medic;

public record DataListenerMedic(
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public DataListenerMedic(Medic medic){
        this(medic.getId(), medic.getNome(), medic.getEmail(), medic.getCrm(), medic.getEspecialidade());
    }
}
