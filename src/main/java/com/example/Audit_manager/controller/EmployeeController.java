package com.example.Audit_manager.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Audit_manager.model.AuditDetails;
import com.example.Audit_manager.model.Employee;
import com.example.Audit_manager.model.EmployeeWithAuditRequest;
import com.example.Audit_manager.repository.AuditDetailsRepository;
import com.example.Audit_manager.repository.EmployeeRepository;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuditDetailsRepository auditDetailsRepository;

    @PostMapping("/createEmployeeWithAudit")
    public ResponseEntity<String> createEmployeeWithAudit(@RequestBody EmployeeWithAuditRequest request) {
        try {
            Employee employee = request.getEmployee();
            AuditDetails auditDetails = request.getAuditDetails();

            // Save Employee details
            Employee savedEmployee = employeeRepository.save(employee);

            // Set Entity details for AuditDetails
            auditDetails.setEntityTableId(savedEmployee.getId());
            auditDetails.setEntityPkId(savedEmployee.getId());

            // Save AuditDetails
            auditDetailsRepository.save(auditDetails);

            return new ResponseEntity<>("Employee and audit details created successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating employee and audit details.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
