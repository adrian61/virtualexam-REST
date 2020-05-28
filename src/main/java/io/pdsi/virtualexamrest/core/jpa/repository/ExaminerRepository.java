package io.pdsi.virtualexamrest.core.jpa.repository;

import io.pdsi.virtualexamrest.core.jpa.entity.Examiner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExaminerRepository extends JpaRepository<Examiner, Integer> {
}