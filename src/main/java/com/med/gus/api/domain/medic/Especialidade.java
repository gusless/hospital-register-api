package com.med.gus.api.domain.medic;

public enum Especialidade {
    ORTOPEDIA("ortopedia"),
    CARDIOLOGIA("cardiologia"),
    GINECOLOGIA("ginecologia"),
    DERMATOLOGIA("dermatologia");

    private String espec;

    Especialidade(String espec) {
        this.espec = espec;
    }

    public String getEspec(){
        return this.espec;
    }
}
