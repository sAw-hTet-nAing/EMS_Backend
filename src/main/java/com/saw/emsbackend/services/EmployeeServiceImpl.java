package com.saw.emsbackend.services;

import com.saw.emsbackend.dto.EmployeeDto;
import com.saw.emsbackend.exception.ResourceNotFoundException;
import com.saw.emsbackend.mapper.EmployeeMapper;
import com.saw.emsbackend.models.Employee;
import com.saw.emsbackend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        // Check if email already exists
        employeeRepository.findByEmail(employeeDto.getEmail())
                .ifPresent(existingEmployee -> {
                    throw new IllegalArgumentException("Email already exists");
                });

        // Check if phone already exists
        employeeRepository.findByPhone(employeeDto.getPhone())
                .ifPresent(existingEmployee -> {
                    throw new IllegalArgumentException("Phone already exists");
                });

        Employee employee = EmployeeMapper.toEmployee(employeeDto);
        Employee saveEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toEmployeeDto(saveEmployee);
    }

    @Override
    public EmployeeDto getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return EmployeeMapper.toEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map(EmployeeMapper::toEmployeeDto).toList();
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        // Check if email already exists
        employeeRepository.findByEmail(employeeDto.getEmail())
                .ifPresent(existingEmployee -> {
                    throw new IllegalArgumentException("Email already exists");
                });

        // Check if phone already exists
        employeeRepository.findByPhone(employeeDto.getPhone())
                .ifPresent(existingEmployee -> {
                    throw new IllegalArgumentException("Phone already exists");
                });

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        employee.setAddress(employeeDto.getAddress());
        Employee updatedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.toEmployeeDto(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        employeeRepository.deleteById(id);
    }


}
