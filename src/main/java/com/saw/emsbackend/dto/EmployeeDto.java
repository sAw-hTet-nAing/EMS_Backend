package com.saw.emsbackend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    @Valid
    private long id;
    @NotNull(message = "first name is required")
    @NotBlank(message = "first name is required")
    private String firstName;
    @NotNull(message = "last name is required")
    @NotBlank(message = "last name is required")
    private String lastName;
    @NotNull(message = "email is required")
    @NotBlank(message = "email is required")
    @Email(message = "email is not valid")
    private String email;
    private String phone;
    private String address;
}
