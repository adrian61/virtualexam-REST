package io.pdsi.virtualexamrest.core.jpa.repository;

import io.pdsi.virtualexamrest.core.jpa.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
}
