package com.med.gus.api.domain.appointment;

import com.med.gus.api.domain.ValidException;
import com.med.gus.api.domain.appointment.validations.schedule.ValidateAppointmentSchedule;
import com.med.gus.api.domain.appointment.validations.cancel.ValidateCancelAppointment;
import com.med.gus.api.domain.medic.Medic;
import com.med.gus.api.domain.medic.MedicRepository;
import com.med.gus.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MedicRepository medicRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<ValidateAppointmentSchedule> validators;

    @Autowired
    private List<ValidateCancelAppointment> cancelValidators;

    public DataDetailScheduleAppointment schedule(DataScheduleAppointment data){
        if (!patientRepository.existsById(data.idPatient())){
            throw new ValidException("Id do paciente informado não existe");
        }
        if(data.idMedic() != null && !medicRepository.existsById(data.idMedic())){
            throw new ValidException("Id do médico informado não existe");
        }

        validators.forEach(v -> v.validate(data));

        var patient = patientRepository.getReferenceById(data.idPatient());
        var medic = findMedic(data);

        if (medic == null){
            throw new ValidException("Não existe médico disponível nessa data");
        }

        var appointment = new Appointment(null, medic, patient, data.data(), null);
        appointmentRepository.save(appointment);

        return new DataDetailScheduleAppointment(appointment);
    }

    private Medic findMedic(DataScheduleAppointment data) {
        if (data.idMedic() != null){
            return medicRepository.getReferenceById(data.idMedic());
        }
        if (data.especialidade() == null){
            throw new ValidException("Especialidade é obrigatória quando médico não for escolhido");
        }

        return medicRepository.findRandomAvailableMedic(data.especialidade(), data.data());
    }

    public void cancelAppointment(DataCancelAppointment data) {
        if(!appointmentRepository.existsById(data.idConsulta())){
            throw new ValidException("Id da consulta informada não existe");
        }

        cancelValidators.forEach(v -> v.validate(data));

        var appointment = appointmentRepository.getReferenceById(data.idConsulta());
        appointment.cancel(data.motivo());
    }
}
