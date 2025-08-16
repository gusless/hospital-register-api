package com.med.gus.api.domain.patient;

import com.med.gus.api.domain.address.Endereco;
import com.med.gus.api.domain.patient.dto.DataRegisterPatient;
import com.med.gus.api.domain.patient.dto.DataUpdatePatient;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;

@Entity(name = "Paciente")
@Table(name = "pacientes")
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + endereco +
                ", ativo=" + ativo +
                '}';
    }

    public Patient() {}

    public Patient(String name, String email, String telefone, String cpf, Endereco endereco) {
        this.nome = name;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
        this.ativo = true;
    }

    public Patient(DataRegisterPatient data){
        this.nome = data.nome();
        this.email = data.email();
        this.telefone = data.telefone();
        this.cpf = data.cpf();
        this.endereco = new Endereco(data.endereco());
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void updateInfos(@Valid DataUpdatePatient data) {
        if (data.nome() != null){
            this.nome = data.nome();
        }
        if (data.telefone() != null){
            this.telefone = data.telefone();
        }
        if (data.endereco() != null){
            this.endereco.updateInfos(data.endereco());
        }
    }

    public void exclude() {
        this.ativo = false;
    }
}
