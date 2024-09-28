package com.saw.emsbackend.mapper;

import com.saw.emsbackend.dto.EmployeeDto;
import com.saw.emsbackend.models.Employee;

public class EmployeeMapper {
    public static EmployeeDto toEmployeeDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhone(), employee.getAddress());
    }

    public static Employee toEmployee(EmployeeDto employeeDto) {
        return new Employee(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail(), employeeDto.getPhone(), employeeDto.getAddress());
    }
}
