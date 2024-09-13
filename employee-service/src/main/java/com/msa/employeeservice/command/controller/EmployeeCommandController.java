package com.msa.employeeservice.command.controller;

import com.msa.employeeservice.command.command.CreateEmployeeCommand;
import com.msa.employeeservice.command.command.DeleteEmployeeCommand;
import com.msa.employeeservice.command.command.UpdateEmployeeCommand;
import com.msa.employeeservice.command.model.CreateEmployeeModel;
import com.msa.employeeservice.command.model.UpdateEmployeeModel;
import jakarta.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeCommandController {

    @Autowired
    private CommandGateway commandGateway;

    @PostMapping
    public String addEmployee(@Valid @RequestBody CreateEmployeeModel employee) {
        CreateEmployeeCommand command = new CreateEmployeeCommand(UUID.randomUUID().toString()
                                                                , employee.getFirstName()
                                                                , employee.getFirstName()
                                                                , employee.getKin()
                                                                , Boolean.FALSE);
        return commandGateway.sendAndWait(command);
    }


    @PutMapping("{employeeId}")
    public String updateEmployee(@Valid @RequestBody UpdateEmployeeModel employee, @PathVariable String employeeId) {
        UpdateEmployeeCommand command = new UpdateEmployeeCommand(employeeId
                , employee.getFirstName()
                , employee.getFirstName()
                , employee.getKin()
                , employee.getIsDisciplined());
        return commandGateway.sendAndWait(command);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId){
        DeleteEmployeeCommand command = new DeleteEmployeeCommand(employeeId);
        return commandGateway.sendAndWait(command);
    }
}
