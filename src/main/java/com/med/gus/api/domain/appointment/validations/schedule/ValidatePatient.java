package com.med.gus.api.domain.appointment.validations.schedule;

import com.med.gus.api.domain.ValidException;
import com.med.gus.api.domain.appointment.DataScheduleAppointment;
import com.med.gus.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatient implements ValidateAppointmentSchedule {

    @Autowired
    private PatientRepository repository;

    public void validate(DataScheduleAppointment data){
        if (!repository.findById(data.idPatient()).get().isAtivo()){
            throw new ValidException("Paciente inativo");
        }
    }
}
