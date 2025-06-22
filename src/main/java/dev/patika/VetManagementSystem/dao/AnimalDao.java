package dev.patika.VetManagementSystem.dao;

import dev.patika.VetManagementSystem.entities.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalDao extends JpaRepository<Animal,Long> {
    boolean existsByName(String name);

    Page<Animal> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Animal> findByCustomerId(Long customerId, Pageable pageable);
}
