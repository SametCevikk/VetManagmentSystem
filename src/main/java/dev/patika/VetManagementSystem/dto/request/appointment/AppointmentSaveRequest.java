package dev.patika.VetManagementSystem.dto.request.appointment;

import dev.patika.VetManagementSystem.entities.Animal;
import dev.patika.VetManagementSystem.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {
    @NotNull
    private LocalDateTime appointmentDate;

    @NotNull
    private Long doctorId;

    @NotNull
    private Long animalId;
}
