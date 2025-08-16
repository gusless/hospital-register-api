package com.med.gus.api.domain.appointment.validations.cancel;

import com.med.gus.api.domain.appointment.dto.DataCancelAppointment;

public interface ValidateCancelAppointment {

    void validate(DataCancelAppointment data);
}
