package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.StudentEntryDto;
import io.pdsi.virtualexamrest.core.jpa.entity.Exam;

import java.util.List;

public interface StudentEntryService {
	List<StudentEntryDto> getStudentEntries();


	StudentEntryDto getStudentEntryById(Integer id);

	List<StudentEntryDto> getStudentEntryDtoByExamId(Exam exam);
}
