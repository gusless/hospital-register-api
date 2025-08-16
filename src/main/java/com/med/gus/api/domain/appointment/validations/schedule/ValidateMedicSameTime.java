package com.med.gus.api.domain.appointment.validations.schedule;

import com.med.gus.api.domain.appointment.AppointmentRepository;
import com.med.gus.api.domain.appointment.dto.DataScheduleAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateMedicSameTime implements ValidateAppointmentSchedule {

    @Autowired
    private AppointmentRepository repository;

    public void validate(DataScheduleAppointment data){
        var medicHaveAnotherAppointmentInSameTime = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(data.idMedic(), data.data());
    }
}
