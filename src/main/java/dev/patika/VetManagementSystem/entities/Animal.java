package dev.patika.VetManagementSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "animals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotNull
   private   String name;

   private String species;

   private String breed;

   private String gender;

   private String colour;

   private LocalDate dateOfBirth;

   @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
   private List<Vaccine> vaccineList;

   @ManyToOne
   @JoinColumn(name = "customer_id", nullable = false)
   private Customer customer;

   @OneToMany(mappedBy = "animal", cascade = CascadeType.REMOVE)
   private List<Appointment> appointmentList;


}
