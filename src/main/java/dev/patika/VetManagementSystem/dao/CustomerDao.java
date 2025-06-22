package dev.patika.VetManagementSystem.dao;

import dev.patika.VetManagementSystem.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Long> {
    boolean existsByMail(String mail);


    Page<Customer> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
