package dev.patika.VetManagementSystem.dao;

import dev.patika.VetManagementSystem.entities.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineDao extends JpaRepository<Vaccine,Long> {
    List<Vaccine> findByProtectionFinishDateBetween(LocalDate start, LocalDate end);

    Page<Vaccine> findByAnimalId(Long id, Pageable pageable);
}
