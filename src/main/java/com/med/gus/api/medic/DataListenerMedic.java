package com.med.gus.api.medic;

public record DataListenerMedic(
        String nome,
        String email,
        String crm,
        Especialidade especialidade
) {
    public DataListenerMedic(Medic medic){
        this(medic.getNome(), medic.getEmail(), medic.getCrm(), medic.getEspecialidade());
    }
}
