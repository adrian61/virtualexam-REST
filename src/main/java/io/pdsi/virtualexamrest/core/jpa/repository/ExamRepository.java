package io.pdsi.virtualexamrest.core.jpa.repository;

import io.pdsi.virtualexamrest.core.jpa.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
}
