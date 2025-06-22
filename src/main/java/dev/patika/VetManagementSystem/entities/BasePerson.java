package dev.patika.VetManagementSystem.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BasePerson {

    @NotBlank(message = "name can not be empty")
    private String name;

    private String phone;
    @Email
    @Column(unique = true,nullable = false)
    private String mail;
    private String address;
    private String city;
}
