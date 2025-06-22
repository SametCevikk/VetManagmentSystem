package dev.patika.VetManagementSystem.dao;

import dev.patika.VetManagementSystem.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment,Long> {


    boolean existsByDoctorIdAndAppointmentDate(Long id, LocalDateTime appointmentDateTime);

    List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime startDate, LocalDateTime finishDate);

    List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime startDateTime, LocalDateTime finishDateTime);
}
