package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.ExamDto;
import io.pdsi.virtualexamrest.api.dto.ExaminerDto;

import java.util.List;

public interface ExaminerService {


	List<ExaminerDto> getExaminersDto();

	ExaminerDto getExaminerDtoById(Integer id);

	boolean createExaminer(ExaminerDto examiner);

	void updateExaminer(ExaminerDto examiner);

	void deleteExaminerById(Integer id);
}
