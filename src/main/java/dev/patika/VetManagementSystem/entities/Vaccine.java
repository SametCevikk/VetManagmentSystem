package dev.patika.VetManagementSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vaccines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaccine {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotNull
   private String name;

   @NotNull
   private String code;

   @NotNull
   private LocalDate protectionStartDate;

   @NotNull
   private LocalDate protectionFinishDate;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "animal_id",nullable = false)
   private Animal animal;


}
