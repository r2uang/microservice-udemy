package com.msa.employeeservice.command.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateEmployeeModel {

    @NotBlank(message = "First name is not blank")
    private String firstName;

    @NotBlank(message = "Last name is not blank")
    private String lastName;

    @NotBlank(message = "Kin is not blank")
    private String kin;

    private Boolean isDisciplined;
}
