package com.saw.emsbackend.services;


import com.saw.emsbackend.dto.DepartmentDto;
import com.saw.emsbackend.dto.EmployeeDto;
import com.saw.emsbackend.response.DefaultResponse;

import java.util.List;

public interface DepartmentService {
    DefaultResponse addEmployeeToDepartment(Long departmentId, Long employeeId);

    DepartmentDto createDepartment(DepartmentDto departmentDto);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto getDepartment(Long id);

    List<EmployeeDto> getEmployeesByDepartment(Long id);
}
