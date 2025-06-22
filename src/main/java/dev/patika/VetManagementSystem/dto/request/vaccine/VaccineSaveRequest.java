package dev.patika.VetManagementSystem.dto.request.vaccine;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccineSaveRequest {

    private String name;

    @NotNull
    private String code;

    private LocalDate protectionStartDate;

    private LocalDate protectionFinishDate;

    @NotNull
    @Positive
    private Long animalId;
}
