package com.med.gus.api.medic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicRepository extends JpaRepository<Medic, Long> {
    Page<Medic> findAllByAtivoTrue(Pageable pageable);
}
