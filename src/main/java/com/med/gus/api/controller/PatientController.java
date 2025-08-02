package com.med.gus.api.controller;

import com.med.gus.api.domain.patient.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registerPatients(@RequestBody @Valid DataRegisterPatient data,
                                           UriComponentsBuilder uriBuilder){
        Patient patient = new Patient(data);
        repository.save(patient);

        var uri = uriBuilder
                .path("/pacientes/{id}")
                .buildAndExpand(patient.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new DataDetailPatient(patient));
    }

    @GetMapping
    public ResponseEntity<Page<DataListenerPatient>> listenerPatients(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        var page = repository.findAllByAtivoTrue(pageable).map(DataListenerPatient::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updatePatient(@RequestBody @Valid DataUpdatePatient data){
        var patient = repository.getReferenceById(data.id());
        patient.updateInfos(data);

        return ResponseEntity.ok(new DataDetailPatient(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id){
        var patient = repository.getReferenceById(id);
        patient.exclude();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detailPatient(@PathVariable Long id){
        var patient = repository.getReferenceById(id);

        return ResponseEntity.ok(new DataDetailPatient(patient));
    }
}
