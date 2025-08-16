package com.med.gus.api.domain.appointment.validations.cancel;

import com.med.gus.api.domain.ValidException;
import com.med.gus.api.domain.appointment.AppointmentRepository;
import com.med.gus.api.domain.appointment.dto.DataCancelAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateCancelTimeBefore implements ValidateCancelAppointment {

    @Autowired
    AppointmentRepository appointmentRepository;

    public void validate(DataCancelAppointment data){

        var appointment = appointmentRepository.findById(data.idConsulta()).get();
        var dateAppointment = appointment.getData();
        var now = LocalDateTime.now();
        var diferenceInHours = Duration.between(now, dateAppointment).toHours();

        if (diferenceInHours < 24){
            throw new ValidException("Consulta deve ser cancelada com antecedência mínima de 24 horas");
        }
    }
}
