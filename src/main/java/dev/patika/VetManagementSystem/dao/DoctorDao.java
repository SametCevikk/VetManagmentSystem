package dev.patika.VetManagementSystem.dao;

import dev.patika.VetManagementSystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorDao extends JpaRepository<Doctor,Long> {
    boolean existsByMail(String mail);
}
