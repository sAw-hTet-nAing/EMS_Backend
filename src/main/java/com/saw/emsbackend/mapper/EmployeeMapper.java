package com.saw.emsbackend.mapper;

import com.saw.emsbackend.dto.EmployeeDto;
import com.saw.emsbackend.models.Department;
import com.saw.emsbackend.models.Employee;

public class EmployeeMapper {
    public static EmployeeDto toEmployeeDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhone(), employee.getAddress()
                , employee.getDepartment().getId(), employee.getDepartment().getName());
    }

    public static Employee toEmployee(EmployeeDto employeeDto, Department department) {
        return new Employee(employeeDto.getId(), employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail(), employeeDto.getPhone(), employeeDto.getAddress(),
                department);
    }
}
