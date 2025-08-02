package com.med.gus.api.domain.appointment;

import com.fasterxml.jackson.databind.annotation.EnumNaming;
import com.med.gus.api.domain.medic.Medic;
import com.med.gus.api.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity(name = "Consulta")
@Table(name = "consultas")
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id")
    private Medic medico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Patient patient;

    private LocalDateTime data;

    @Column(name = "motivo_cancelamento")
    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;


    @Override
    public String toString() {
        return ", medico=" + medico +
                ", patient=" + patient +
                ", data=" + data +
                '}';
    }

    public Appointment() {}

    public Appointment(Long id, Medic medico, Patient patient, LocalDateTime data, MotivoCancelamento motivo) {
        this.id = id;
        this.medico = medico;
        this.patient = patient;
        this.data = data;
        this.motivoCancelamento = motivo;
    }

    public Long getId() {
        return id;
    }

    public Medic getMedico() {
        return medico;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void cancel(MotivoCancelamento motivo) {
        this.motivoCancelamento = motivo;
    }
}
