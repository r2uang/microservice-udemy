package com.msa.employeeservice.command.event;

import lombok.Data;

@Data
public class EmployeeCreateEvent {
    private String id;
    private String firstName;
    private String lastName;
    private String kin;
    private Boolean isDisciplined;
}
