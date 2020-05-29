package io.pdsi.virtualexamrest.core.jpa.repository;

import io.pdsi.virtualexamrest.core.jpa.entity.Exam;
import io.pdsi.virtualexamrest.core.jpa.entity.Examiner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
	List<Exam> getAllByExaminerId(Examiner examiner);
}
