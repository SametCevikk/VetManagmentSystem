package dev.patika.VetManagementSystem.dao;

import dev.patika.VetManagementSystem.entities.AvailableDate;
import dev.patika.VetManagementSystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AvailableDateDao extends JpaRepository<AvailableDate,Long> {
    boolean existsByAvailableDate(LocalDate availableDate);

    boolean existsByDoctorAndAvailableDate(Doctor doctor, LocalDate availableDate);
}
