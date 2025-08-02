package com.med.gus.api.domain.medic;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicRepository extends JpaRepository<Medic, Long> {

    Page<Medic> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            SELECT m FROM Medico m
            WHERE
            m.ativo = true
            AND
            m.especialidade = :especialidade
            AND
            m.id not in(
                SELECT c.medico.id FROM Consulta c
                WHERE
                c.data = :date
                AND
                c.motivoCancelamento is null
            )
            ORDER BY rand()
            limit 1
            """)
    Medic findRandomAvailableMedic(Especialidade especialidade, @NotNull @Future LocalDateTime date);
}
