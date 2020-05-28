package io.pdsi.virtualexamrest.core.jpa.repository;

import io.pdsi.virtualexamrest.core.jpa.entity.StudentEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEntryRepository extends JpaRepository<StudentEntry, Integer> {
}
