package com.med.gus.api.controller;

import com.med.gus.api.patient.DataRegisterPatient;
import com.med.gus.api.patient.Patient;
import com.med.gus.api.patient.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    @Autowired
    PatientRepository repository;

    @PostMapping
    @Transactional
    public void registerPatients(@RequestBody @Valid DataRegisterPatient data){
        repository.save(new Patient(data));
    }
}
