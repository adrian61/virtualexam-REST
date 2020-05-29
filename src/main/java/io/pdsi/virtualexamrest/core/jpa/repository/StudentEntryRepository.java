package io.pdsi.virtualexamrest.core.jpa.repository;

import io.pdsi.virtualexamrest.core.jpa.entity.Exam;
import io.pdsi.virtualexamrest.core.jpa.entity.StudentEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentEntryRepository extends JpaRepository<StudentEntry, Integer> {
	List<StudentEntry> getAllByExam(Exam exam);
}
