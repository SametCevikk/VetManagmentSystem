package dev.patika.VetManagementSystem.dto.request.doctor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSaveRequest {
    @NotBlank(message = "name can not be empty")
    private String name;

    private String phone;
    @Email
    @NotBlank(message = "mail can not be empty")
    private String mail;
    private String address;
    private String city;
}
