package com.saw.emsbackend.controllers;


import com.saw.emsbackend.dto.DepartmentDto;
import com.saw.emsbackend.response.DefaultResponse;
import com.saw.emsbackend.services.DepartmentServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentServiceImpl departmentService;


    @PostMapping(value = "/create")
    public ResponseEntity<DepartmentDto> createDepartment(@Valid @RequestParam("name") String name) {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setName(name);
        DepartmentDto savedDepartmentDto = departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(savedDepartmentDto, HttpStatus.CREATED);
    }


    @PostMapping(value = "/addEmployee")
    public ResponseEntity<DefaultResponse> addEmployeeToDepartment(@RequestParam("departmentId") Long departmentId, @RequestParam("employeeId") Long employeeId) {
        DefaultResponse defaultResponse = departmentService.addEmployeeToDepartment(departmentId, employeeId);
        return new ResponseEntity<>(defaultResponse, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();
        return new ResponseEntity<>(departmentDtos, HttpStatus.OK);
    }


}
