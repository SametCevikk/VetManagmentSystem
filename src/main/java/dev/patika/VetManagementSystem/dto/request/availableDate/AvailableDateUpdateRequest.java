package dev.patika.VetManagementSystem.dto.request.availableDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableDateUpdateRequest {
    @NotNull
    @Positive
    private Long id;
    @NotNull
    private LocalDate availableDate;

    @NotNull
    private Long doctorId;
}
