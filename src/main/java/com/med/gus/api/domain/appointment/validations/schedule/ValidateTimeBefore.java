package com.med.gus.api.domain.appointment.validations.schedule;

import com.med.gus.api.domain.ValidException;
import com.med.gus.api.domain.appointment.DataScheduleAppointment;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateTimeBefore implements ValidateAppointmentSchedule {

    public void validate(DataScheduleAppointment data){
        var dateAppointment = data.data();
        var now = LocalDateTime.now();
        var diferenceInMinutes = Duration.between(now, dateAppointment).toMinutes();

        if (diferenceInMinutes < 30){
            throw new ValidException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}
