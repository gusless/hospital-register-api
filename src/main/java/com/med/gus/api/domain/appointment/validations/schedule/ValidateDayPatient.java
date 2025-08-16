package com.med.gus.api.domain.appointment.validations.schedule;

import com.med.gus.api.domain.ValidException;
import com.med.gus.api.domain.appointment.AppointmentRepository;
import com.med.gus.api.domain.appointment.dto.DataScheduleAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDayPatient implements ValidateAppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(DataScheduleAppointment data){
        var firstTime = data.data().withHour(7);
        var lastTime = data.data().withHour(18);
        var patientHaveAnotherAppointmentInDay = appointmentRepository.existsByPatientIdAndDataBetween(data.idPatient(),firstTime,lastTime);
        if (patientHaveAnotherAppointmentInDay) {
            throw new ValidException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}
