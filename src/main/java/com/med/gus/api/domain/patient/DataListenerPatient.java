package com.med.gus.api.domain.patient;

public record DataListenerPatient(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public DataListenerPatient(Patient patient){
        this(patient.getId(), patient.getNome(), patient.getEmail(), patient.getCpf());
    }
}
