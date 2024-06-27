package com.application.usermanagement.repository;

import com.application.usermanagement.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Optional<Department> findByName(String name);
}
