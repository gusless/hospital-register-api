package com.med.gus.api.controller;

import com.med.gus.api.domain.appointment.AppointmentSchedule;
import com.med.gus.api.domain.appointment.dto.DataCancelAppointment;
import com.med.gus.api.domain.appointment.dto.DataScheduleAppointment;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentSchedule schedule;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid DataScheduleAppointment data){
        var dto = schedule.schedule(data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid DataCancelAppointment data){
        schedule.cancelAppointment(data);
        return ResponseEntity.noContent().build();
    }

}
