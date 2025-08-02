package com.med.gus.api.controller;

import com.med.gus.api.domain.appointment.AppointmentSchedule;
import com.med.gus.api.domain.appointment.DataDetailScheduleAppointment;
import com.med.gus.api.domain.appointment.DataScheduleAppointment;
import com.med.gus.api.domain.medic.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DataScheduleAppointment> dataScheduleAppointmentJson;

    @Autowired
    private JacksonTester<DataDetailScheduleAppointment> dataDetailScheduleAppointmentJson;

    @MockitoBean
    private AppointmentSchedule appointmentSchedule;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void schedule1() throws Exception {
        var response = mvc
                .perform(post("/consultas"))
                .andReturn()
                .getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void schedule2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        var especialidade = Especialidade.CARDIOLOGIA;

        var dataDetail = new DataDetailScheduleAppointment(null, 2l, 5l, date);
        when(appointmentSchedule.schedule(any()))
                .thenReturn(dataDetail);

        var response = mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dataScheduleAppointmentJson.write(
                                        new DataScheduleAppointment(2l, 5l, date, especialidade)
                                ).getJson())
                )
                .andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var jsonExpected = dataDetailScheduleAppointmentJson.write(
                dataDetail
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonExpected);
    }

}