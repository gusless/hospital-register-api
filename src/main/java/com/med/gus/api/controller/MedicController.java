package com.med.gus.api.controller;

import com.med.gus.api.medic.DataRegisterMedic;
import com.med.gus.api.medic.Medic;
import com.med.gus.api.medic.MedicRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicController {

    @Autowired
    private MedicRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DataRegisterMedic data){
        repository.save(new Medic(data));
    }
}
