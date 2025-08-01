package com.med.gus.api.domain.appointment.validations.schedule;

import com.med.gus.api.domain.ValidException;
import com.med.gus.api.domain.appointment.DataScheduleAppointment;
import com.med.gus.api.domain.medic.MedicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateMedic implements ValidateAppointmentSchedule {

    @Autowired
    private MedicRepository repository;

    public void validate(DataScheduleAppointment data){

        if (data.idMedic() == null){
            return;
        }

        if (!repository.findById(data.idMedic()).get().isAtivo()){
            throw new ValidException("Médico inativo");
        }
    }
}
