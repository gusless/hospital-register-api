package com.med.gus.api.controller;

import com.med.gus.api.patient.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public Page<DataListenerPatient> listenerPatients(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return repository.findAll(pageable).map(DataListenerPatient::new);
    }

    @PutMapping
    @Transactional
    public void updatePatient(@RequestBody @Valid DataUpdatePatient data){
        var patient = repository.getReferenceById(data.id());
        patient.updateInfos(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletePatient(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        patient.exclude();
    }
}
