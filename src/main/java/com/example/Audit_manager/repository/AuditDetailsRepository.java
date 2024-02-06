package com.example.Audit_manager.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Audit_manager.model.AuditDetails;

public interface AuditDetailsRepository extends JpaRepository<AuditDetails, Long> {
}
