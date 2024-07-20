package com.SampleProjSpring.Demo.sevices;

import ch.qos.logback.core.model.Model;
import com.SampleProjSpring.Demo.dto.EmployeeDTO;
import com.SampleProjSpring.Demo.entities.EmployeeEntity;
import com.SampleProjSpring.Demo.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper =modelMapper;
    }



    public Optional<EmployeeDTO> findById(long Id) {
        //Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
        return  employeeRepository.findById(Id).map(EmployeeEntity-> modelMapper.map(EmployeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> findAll() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee( EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity savedEmployee = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployee,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployee(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity =employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

    public boolean deleteEmployee(Long employeeId) {
        boolean exist = employeeRepository.existsById(employeeId);
        if(!exist){
            return false;
        }
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO patchEmployee(Map<String, Object> updates, Long employeeId) {
        boolean exist = employeeRepository.existsById(employeeId);
        if(!exist){
            return null;
        }
        EmployeeEntity employeeEntity =employeeRepository.findById(employeeId).get();
        updates.forEach((field,value) -> {
            Field fieldTobeUpdated = ReflectionUtils.findField(EmployeeEntity.class ,field);
            fieldTobeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldTobeUpdated,employeeEntity,value);
        });

        return modelMapper.map(employeeRepository.save(employeeEntity),EmployeeDTO.class);
    }
}
