package com.med.gus.api.controller;

import com.med.gus.api.medic.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public void registerMedics(@RequestBody @Valid DataRegisterMedic data){
        repository.save(new Medic(data));
    }

    @GetMapping
    public Page<DataListenerMedic> listenerMedics(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return repository.findAllByAtivoTrue(pageable).map(DataListenerMedic::new);
    }

    @PutMapping
    @Transactional
    public void updateMedic(@RequestBody @Valid DataUpdateMedic data) {
        var medic = repository.getReferenceById(data.id());
        medic.updateInfos(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteMedic(@PathVariable Long id){
        //repository.deleteById(id);
        var medic = repository.getReferenceById(id);
        medic.exclude();
    }

}
