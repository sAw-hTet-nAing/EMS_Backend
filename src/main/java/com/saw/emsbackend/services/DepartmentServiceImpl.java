package com.saw.emsbackend.services;

import com.saw.emsbackend.dto.DepartmentDto;
import com.saw.emsbackend.dto.EmployeeDto;
import com.saw.emsbackend.exception.ResourceNotFoundException;
import com.saw.emsbackend.mapper.DepartmentMapper;
import com.saw.emsbackend.mapper.EmployeeMapper;
import com.saw.emsbackend.models.Department;
import com.saw.emsbackend.models.Employee;
import com.saw.emsbackend.repository.DepartmentRepository;
import com.saw.emsbackend.repository.EmployeeRepository;
import com.saw.emsbackend.response.DefaultResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public DefaultResponse addEmployeeToDepartment(Long departmentId, Long employeeId) {
        // Fetch department from the repository
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        // Fetch employee from the repository
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // Set department to employee (no need for manual DTO to entity conversion)
        employee.setDepartment(department);

        // Add employee to department's employee list, if not already present
        if (!department.getEmployees().contains(employee)) {
            department.getEmployees().add(employee);
        }

        // Save the employee and department (if necessary)
        employeeRepository.save(employee);
        departmentRepository.save(department);

        return new DefaultResponse("Employee added to department successfully");
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        departmentRepository.findByName(departmentDto.getName())
                .ifPresent(existingDepartment -> {
                    throw new IllegalArgumentException("Department already exists");
                });
        Department department = DepartmentMapper.toDepartment(departmentDto);
        Department saveDepartment = departmentRepository.save(department);
        return DepartmentMapper.toDepartmentDto(saveDepartment);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(DepartmentMapper::toDepartmentDto).toList();
    }

    @Override
    public DepartmentDto getDepartment(Long id) {
        return departmentRepository.findById(id)
                .map(DepartmentMapper::toDepartmentDto)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
    }

    @Override
    public List<EmployeeDto> getEmployeesByDepartment(Long id) {
        DepartmentDto departmentDto = getDepartment(id);
        return departmentDto.getEmployees().stream().map(EmployeeMapper::toEmployeeDto).toList();
    }
}
