package dev.patika.VetManagementSystem.dto.response.vaccine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VaccineWithAnimalResponse {
    private String vaccineName;
    private String code;
    private LocalDate protectionStartDate;
    private LocalDate protectionFinishDate;

    private Long animalId;
    private String animalName;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
}
