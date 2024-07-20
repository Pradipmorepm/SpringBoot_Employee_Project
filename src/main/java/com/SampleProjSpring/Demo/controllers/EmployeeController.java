package com.SampleProjSpring.Demo.controllers;

import com.SampleProjSpring.Demo.dto.EmployeeDTO;
import com.SampleProjSpring.Demo.entities.EmployeeEntity;
import com.SampleProjSpring.Demo.repositories.EmployeeRepository;
import com.SampleProjSpring.Demo.sevices.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @GetMapping(path = "/{EmpId}")
//    public EmployeeDTO getEmployeeById(@PathVariable(name="EmpId") long Id){
//        return new EmployeeDTO(Id, "pradip", "pradipmorepm15@gmail.com", 23, LocalDate.of(2023, 8,21),true);
//    }
    @GetMapping(path = "/{EmpId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable(name="EmpId") long Id){
        Optional<EmployeeDTO> employeeDTO = employeeService.findById(Id);
        return employeeDTO
            .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/getAllEmp")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){

        return ResponseEntity.ok(employeeService.findAll());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
         EmployeeDTO savedEmployee = employeeService.createNewEmployee(inputEmployee);
         return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long employeeId){
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, employeeDTO));
    }

    @DeleteMapping(path = "/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Long employeeId){
        boolean deleted = employeeService.deleteEmployee(employeeId);
        if(deleted){
            return ResponseEntity.ok(true);
        }
        else return ResponseEntity.notFound().build();

    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> patchEmployee(@RequestBody Map<String , Object> updates ,@PathVariable Long employeeId){
            EmployeeDTO employeeDTO = employeeService.patchEmployee(updates, employeeId);
            if(employeeDTO == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(employeeDTO);
    }
}


