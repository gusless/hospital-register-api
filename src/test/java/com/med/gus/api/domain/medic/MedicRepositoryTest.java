package com.med.gus.api.domain.medic;

import com.med.gus.api.domain.address.dto.DataAddress;
import com.med.gus.api.domain.appointment.Appointment;
import com.med.gus.api.domain.medic.dto.DataRegisterMedic;
import com.med.gus.api.domain.patient.dto.DataRegisterPatient;
import com.med.gus.api.domain.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicRepositoryTest {

    @Autowired
    MedicRepository medicRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void findRandomAvailableMedic1() {
        // given / arrange
        var nextMondayAt10AM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medic = registerMedic("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var patient = registerPatient("Paciente", "paciente@gmail.com", "00000000000");
        registerAppointment(medic, patient, nextMondayAt10AM);

        // when / act
        var medicAvailable = medicRepository.findRandomAvailableMedic(Especialidade.CARDIOLOGIA, nextMondayAt10AM);

        // then / assert
        assertThat(medicAvailable).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void findRandomAvailableMedic2() {
        // given / arrange
        var nextMondayAt10AM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        var medic =registerMedic("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        // when / act
        var medicAvailable = medicRepository.findRandomAvailableMedic(Especialidade.CARDIOLOGIA, nextMondayAt10AM);

        // then / assert
        assertThat(medicAvailable).isEqualTo(medic);
    }

    private void registerAppointment(Medic medic, Patient patient, LocalDateTime date){
        entityManager.persist(new Appointment(null, medic, patient, date, null));
    }

    private Medic registerMedic(String nome, String email, String crm, Especialidade especialidade){
        var medic = new Medic(dataMedic(nome, email, crm, especialidade));
        entityManager.persist(medic);
        return medic;
    }

    private Patient registerPatient(String nome, String email, String cpf){
        var patient = new Patient(dataPatient(nome, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private DataRegisterMedic dataMedic(String nome, String email, String crm, Especialidade especialidade) {
        return new DataRegisterMedic(
                nome,
                email,
                "84933334444",
                crm,
                especialidade,
                dataAddress()
        );
    }

    private DataRegisterPatient dataPatient(String nome, String email, String cpf){
        return new DataRegisterPatient(
                nome,
                email,
                "84911112222",
                cpf,
                dataAddress()
        );
    }

    private DataAddress dataAddress(){
        return new DataAddress(
                "rua x",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }

}