package com.med.gus.api.patient;

public record DataListenerPatient(
        String nome,
        String email,
        String cpf
) {
    public DataListenerPatient(Patient patient){
        this(patient.getNome(), patient.getEmail(), patient.getCpf());
    }
}
