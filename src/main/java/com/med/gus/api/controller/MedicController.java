package com.med.gus.api.controller;

import com.med.gus.api.domain.medic.*;
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
@RequestMapping("/medicos")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registerMedics(@RequestBody @Valid DataRegisterMedic data,
                                         UriComponentsBuilder uriBuilder){
        var medic = new Medic(data);
        repository.save(medic);

        var uri = uriBuilder
                .path("/medicos/{id}")
                .buildAndExpand(medic.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(new DataDetailMedic(medic));
    }

    @GetMapping
    public ResponseEntity<Page<DataListenerMedic>> listenerMedics(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        var page = repository.findAllByAtivoTrue(pageable).map(DataListenerMedic::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateMedic(@RequestBody @Valid DataUpdateMedic data) {
        var medic = repository.getReferenceById(data.id());
        medic.updateInfos(data);

        return ResponseEntity.ok(new DataDetailMedic(medic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteMedic(@PathVariable Long id){
        var medic = repository.getReferenceById(id);
        medic.exclude();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detailMedic(@PathVariable Long id){
        var medic = repository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetailMedic(medic));
    }
}
