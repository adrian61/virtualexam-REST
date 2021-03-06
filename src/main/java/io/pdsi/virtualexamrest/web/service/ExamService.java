package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.ExamDto;

import java.util.List;

public interface ExamService {
	List<ExamDto> getExamsDto();

	ExamDto getExamDtoById(Integer id);

	boolean createExam(ExamDto examDto);

	void updateExam(ExamDto exam);

	void deleteExamById(Integer id);
}
