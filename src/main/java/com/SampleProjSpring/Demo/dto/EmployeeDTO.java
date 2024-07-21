package com.SampleProjSpring.Demo.dto;

import com.SampleProjSpring.Demo.annotations.EmployeeAgeValidation;
import com.SampleProjSpring.Demo.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private long Id;
    @NotBlank(message = "name can not be blank")
    @Size(min = 3 ,max = 10 , message = "name size should be greater than 3 and less than 10")
    private String name;

    @NotBlank
    @Email(message = "Email should be a valid email")
    private String email;

    //this is custom annotation for practice that will check for age should be prime

    @EmployeeAgeValidation
    @Max(value = 80 , message = "age can not be greater than 80")
    @Min(value = 18, message = "minage should be 18")
    private Integer age;

    @NotBlank
    //@Pattern(regexp = "^(ADMIN|USER)$")
    //Also we can make our custom validation  for this
    //like
    @EmployeeRoleValidation
    private String role;

    @Past
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;

//    public EmployeeDTO(){
//
//    }
//
//    public void setId(long id) {
//        Id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public void setDateOfJoining(LocalDate dateOfJoining) {
//        this.dateOfJoining = dateOfJoining;
//    }
//
//    public void setActive(Boolean active) {
//        isActive = active;
//    }
//
//    public long getId() {
//        return Id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public LocalDate getDateOfJoining() {
//        return dateOfJoining;
//    }
//
//    public Boolean getActive() {
//        return isActive;
//    }
//
//    public EmployeeDTO(long id, String name, String email, Integer age, LocalDate dateOfJoining, Boolean isActive) {
//        Id = id;
//        this.name = name;
//        this.email = email;
//        this.age = age;
//        this.dateOfJoining = dateOfJoining;
//        this.isActive = isActive;
//    }
}
