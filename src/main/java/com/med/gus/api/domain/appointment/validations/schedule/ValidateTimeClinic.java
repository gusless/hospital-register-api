package com.med.gus.api.domain.appointment.validations.schedule;

import com.med.gus.api.domain.ValidException;
import com.med.gus.api.domain.appointment.DataScheduleAppointment;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidateTimeClinic implements ValidateAppointmentSchedule {

    public void validate(DataScheduleAppointment data) {
        var dateAppointment = data.data();
        var sunday = dateAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeClinicOpen = dateAppointment.getHour() < 7;
        var afterClinicClose = dateAppointment.getHour() > 18;
        if(sunday || beforeClinicOpen || afterClinicClose){
            throw new ValidException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
