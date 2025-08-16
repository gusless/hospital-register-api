package com.med.gus.api.domain.appointment.validations.schedule;

import com.med.gus.api.domain.appointment.dto.DataScheduleAppointment;

public interface ValidateAppointmentSchedule {
    void validate(DataScheduleAppointment data);
}
