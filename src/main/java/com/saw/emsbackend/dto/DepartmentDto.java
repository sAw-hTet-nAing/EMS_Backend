package com.saw.emsbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saw.emsbackend.models.Employee;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private long id;
    @NotNull
    private String name;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Employee> employees;
}
