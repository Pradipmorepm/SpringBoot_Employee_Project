package com.SampleProjSpring.Demo.repositories;

import com.SampleProjSpring.Demo.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {

}
