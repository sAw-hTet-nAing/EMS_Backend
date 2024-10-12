package com.saw.emsbackend.mapper;

import com.saw.emsbackend.dto.DepartmentDto;
import com.saw.emsbackend.models.Department;

public class DepartmentMapper {

    public static DepartmentDto toDepartmentDto(Department department) {
        return new DepartmentDto(department.getId(), department.getName(), department.getEmployees());
    }

    public static Department toDepartment(DepartmentDto departmentDto) {
        return new Department(departmentDto.getId(), departmentDto.getName(), departmentDto.getEmployees());
    }
}
